package com.numbers.controller.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeletedUserResponse implements Serializable {
    private String username;
    private MessageRes messageRes;
}
