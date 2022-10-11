package com.numbers.controller.user;

import com.numbers.utils.Status;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class SingleUserResponse implements Serializable {
    private String username;
    private byte[] password;
    private Status status;
    private LocalDate createdOn;
}
