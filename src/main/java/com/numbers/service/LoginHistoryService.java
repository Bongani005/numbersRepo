package com.numbers.service;

import com.numbers.controller.user.LoginHistoryResponse;
import com.numbers.entities.LoginHistory;
import com.numbers.entities.User;
import com.numbers.repository.LoginHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginHistoryService {

    @Autowired
    public LoginHistoryRepository repository;

    public List<LoginHistoryResponse> getAllHistory(){
        List<LoginHistoryResponse> loginHistoryResponses = new ArrayList<>();

        for (LoginHistory loginHistory: repository.findAll()){
            LoginHistoryResponse response = new LoginHistoryResponse();
            response.setHistoryId(loginHistory.getHistoryId());
            response.setPassword(loginHistory.getPassword());
            response.setLoggedInOnDate(loginHistory.getLoggedInOnDate());
            Optional<User> optional= loginHistory.getUsers().stream().findFirst();
            response.setUsername(optional.get().getUsername());
             loginHistoryResponses.add(response);
        }

        return loginHistoryResponses;
    }
}
