package com.numbers.entities.factory;

import com.numbers.entities.LoginHistory;
import com.numbers.entities.User;
import com.numbers.entities.UserRequest;
import com.numbers.entities.VerifyUser;
import com.numbers.utils.Status;
import com.numbers.utils.TripleDes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class ObjectFactory {


    public static User getUserObject(UserRequest request) throws Exception {

        byte[] password = TripleDes.getInstance().tripleDesEncrypt(request.getPassword());
        return new User(
                request.getUsername(), password, Status.INACTIVE, LocalDate.now()
        );
    }

    public static VerifyUser getVerifyUser(byte[] password, String username){
        return new VerifyUser(
                UUID.randomUUID().toString(), password, LocalDate.now(), username
        );
    }

    public static LoginHistory getLoginHistory(User user){
        LoginHistory loginHistory = new LoginHistory(
                UUID.randomUUID().toString().substring(0,16), user.getPassword(), LocalDateTime.now()
        );

        loginHistory.getUsers().add(user);

        return loginHistory;
    }
}
