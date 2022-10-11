package com.numbers.controller.user;

import com.numbers.utils.Status;
import lombok.Data;

import java.io.Serializable;

@Data
public class VerificationResponse implements Serializable {

    private String username;
    private Status status;
    private MessageRes messageRes;
}
