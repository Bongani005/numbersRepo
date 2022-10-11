package com.numbers.entities;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "login_history")
@Getter
@Setter
public class LoginHistory implements Serializable {
    private static final long serialVersionUID = 4150153570742425476L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "historyId")
    private String historyId;

    @NotNull
    @Column(name = "password", length = 5000)
    private byte[] password;

    @NotNull
    @Column(name = "loggedInOnDate")
    private LocalDateTime loggedInOnDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_login_history", joinColumns =
            @JoinColumn(name = "historyId", referencedColumnName = "historyId"),
            inverseJoinColumns = @JoinColumn(name = "username", referencedColumnName = "username")
    )
    private List<User> users = new ArrayList<>();

    public LoginHistory() {
    }

    public LoginHistory(String historyId, byte[] password, LocalDateTime loggedInOnDate) {
        this.historyId = historyId;
        this.password = password;
        this.loggedInOnDate = loggedInOnDate;
    }
}
