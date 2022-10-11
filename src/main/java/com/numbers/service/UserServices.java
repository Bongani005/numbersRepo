package com.numbers.service;

import com.numbers.controller.user.*;
import com.numbers.entities.LoginHistory;
import com.numbers.entities.User;
import com.numbers.entities.UserRequest;
import com.numbers.entities.VerifyUser;
import com.numbers.entities.factory.ObjectFactory;
import com.numbers.repository.LoginHistoryRepository;
import com.numbers.repository.UserRepository;
import com.numbers.repository.VerifyUserRepository;
import com.numbers.utils.Status;
import com.numbers.utils.TripleDes;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    public UserRepository repository;

    @Autowired
    public VerifyUserRepository verifyUserRepository;

    @Autowired
    public LoginHistoryRepository loginHistoryRepository;

    public UserResponse save(UserRequest request){
        UserResponse response = null;

        if(repository.existsById(request.getUsername())){
            response = new UserResponse();
            response.setUsername(request.getUsername());
            response.setStatus(Status.INACTIVE);
            response.setVerificationCode("");
            response.setMessage(MessageRes.UsernameExist);
        }else {
            User user = null;

            try {
                user = ObjectFactory.getUserObject(request);

                VerifyUser verifyUser1 = ObjectFactory.getVerifyUser(user.getPassword(), user.getUsername());

                verifyUserRepository.save(verifyUser1);
                user = repository.save(user);
                repository.flush();
                response = new UserResponse();
                response.setUsername(user.getUsername());
                response.setStatus(user.getStatus());
                response.setVerificationCode(verifyUser1.getVerificationCode());
                response.setMessage(MessageRes.Successful);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return response;
    }

    public List<SingleUserResponse> getAllUsers(){
        List<SingleUserResponse> singleUserResponses = new ArrayList<>();
        for (User user: repository.findAll()){
            SingleUserResponse userResponse = new SingleUserResponse();
            userResponse.setUsername(user.getUsername());
            userResponse.setPassword(user.getPassword());
            userResponse.setStatus(user.getStatus());
            userResponse.setCreatedOn(user.getCreatedOn());
            singleUserResponses.add(userResponse);
        }
        return singleUserResponses;
    }
//
    public SingleUserResponse getUserById(String username){
        Optional<User> user = repository.findById(username);
        SingleUserResponse response = new SingleUserResponse();
        response.setUsername(user.get().getUsername());
        response.setPassword(user.get().getPassword());
        response.setStatus(user.get().getStatus());
        response.setCreatedOn(user.get().getCreatedOn());
        return response;
    }

    public DeletedUserResponse deleteUser(String username){
        DeletedUserResponse deletedUserResponse = null;
        if(repository.existsById(username)){
            Optional<User> user = repository.findById(username);
            User user1 = new User(
                    user.get().getUsername(),user.get().getPassword(), Status.DEACTIVATED,user.get().getCreatedOn()
            );
            repository.save(user1);
            repository.flush();

            deletedUserResponse = new DeletedUserResponse();
            deletedUserResponse.setUsername(username);
            deletedUserResponse.setMessageRes(MessageRes.UsernameDeleted);

        }else {
            deletedUserResponse = new DeletedUserResponse();
            deletedUserResponse.setUsername(username);
            deletedUserResponse.setMessageRes(MessageRes.Failed);
        }
        return deletedUserResponse;
    }

    public LoginResponse login(UserRequest request){
        Optional<User> user = repository.findById(request.getUsername());
        LoginResponse response = null;
        if (StringUtils.equals(user.get().getStatus().name(), Status.ACTIVE.name())){
            try{
                String password = TripleDes.getInstance().tripleDesDecrypt(user.get().getPassword());
                if(StringUtils.equals(password,request.getPassword())){
                    LoginHistory loginHistory = ObjectFactory.getLoginHistory(user.get());
                    user.get().getLoginHistories().add(loginHistory);
                    loginHistoryRepository.save(loginHistory);
                    loginHistoryRepository.flush();

                    response = new LoginResponse();
                    response.setUsername(user.get().getUsername());
                    response.setStatus(user.get().getStatus());
                    response.setMessageRes(MessageRes.Successful);
                }else{
                    response = new LoginResponse();
                    response.setUsername(user.get().getUsername());
                    response.setStatus(user.get().getStatus());
                    response.setMessageRes(MessageRes.InvalidCredentials);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else {
            response = new LoginResponse();
            response.setUsername(user.get().getUsername());
            response.setStatus(user.get().getStatus());
            response.setMessageRes(MessageRes.Failed);
        }

        return response;
    }
    public VerificationResponse activateUser(String verificationCode){
        VerificationResponse verificationResponse = new VerificationResponse();

        VerifyUser verifyUser = verifyUserRepository.getReferenceById(verificationCode);
        User user = repository.getReferenceById(verifyUser.getUsername());
        User toUpdate = repository.save(new User(user.getUsername(),user.getPassword(), Status.ACTIVE,user.getCreatedOn()));
        if(!toUpdate.getUsername().isEmpty()){
            verifyUserRepository.delete(verifyUser);
            verificationResponse.setUsername(toUpdate.getUsername());
            verificationResponse.setStatus(toUpdate.getStatus());
            verificationResponse.setMessageRes(MessageRes.Successful);
            repository.flush();
            return verificationResponse;
        }else {
            verificationResponse.setUsername("");
            verificationResponse.setStatus(Status.INACTIVE);
            verificationResponse.setMessageRes(MessageRes.Failed);
            return verificationResponse;
        }
    }
}
