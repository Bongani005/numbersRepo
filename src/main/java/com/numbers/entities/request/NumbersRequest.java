package com.numbers.entities.request;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class NumbersRequest implements Serializable {
    private Integer numberOne;
    private Integer numberTwo;
    private Integer numberThree;
    private Integer numberFour;
    private Integer numberFive;
    private Integer numberSix;
    private Integer numberBonus;
    private String resultDate;
}
