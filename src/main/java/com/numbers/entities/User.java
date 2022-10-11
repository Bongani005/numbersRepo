package com.numbers.entities;

import com.numbers.utils.Status;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User implements Serializable {
    private static final long serialVersionUID = -7492821652525698356L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "username", length = 200)
    private String username;

    @NotNull
    @Column(name = "password", length = 5000)
    private byte[] password;

    @NotNull
    @Column(name = "status", length = 45)
    @Enumerated(EnumType.STRING)
    private Status status;

    @NotNull
    @Column(name = "createdOn")
    private LocalDate createdOn;

    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "users")
    private List<LoginHistory> loginHistories = new ArrayList<>();

    public User() {
    }

    public User(String username, byte[] password,Status status, LocalDate createdOn) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.createdOn = createdOn;
    }
}
