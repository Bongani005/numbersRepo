package com.numbers.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "verify_user")
@Getter
@Setter
public class VerifyUser implements Serializable {
    private static final long serialVersionUID = 7227491084141116499L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "verificationCode")
    private String verificationCode;

    @NotNull
    @Column(name = "password", length = 5000)
    private byte[] password;

    @NotNull
    @Column(name = "createdOn")
    private LocalDate createdOn;

    @NotNull
    @Column(name = "username", length = 200)
    private String username;

    public VerifyUser() {
    }

    public VerifyUser(String verificationCode, byte[] password, LocalDate createdOn, String username) {
        this.verificationCode = verificationCode;
        this.password = password;
        this.createdOn = createdOn;
        this.username = username;
    }
}
