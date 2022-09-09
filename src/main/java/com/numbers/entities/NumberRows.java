package com.numbers.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class NumberRows implements Serializable {
    private Integer numberOne;
    private Integer numberTwo;
    private Integer numberThree;
    private Integer numberFour;
    private Integer numberFive;
    private Integer numberSix;
    private Integer numberBonus;
}
