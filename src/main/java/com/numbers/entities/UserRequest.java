package com.numbers.entities;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class UserRequest implements Serializable {
    private String username;
    private String password;
}
