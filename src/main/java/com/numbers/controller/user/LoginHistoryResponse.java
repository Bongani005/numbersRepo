package com.numbers.controller.user;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class LoginHistoryResponse implements Serializable {
    private String username;
    private LocalDateTime loggedInOnDate;
    private byte[] password;
    private String historyId;
    Integer val;
}
