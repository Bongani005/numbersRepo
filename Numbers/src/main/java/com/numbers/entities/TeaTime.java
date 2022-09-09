package com.numbers.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "TeaTime")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TeaTime extends AbstractId implements Serializable {
    @Embedded
    private NumberRows numberRows;
}
