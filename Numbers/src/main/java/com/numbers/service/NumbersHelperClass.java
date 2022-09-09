package com.numbers.service;

import com.numbers.entities.NumberRows;
import com.numbers.entities.request.NumbersRequest;

import java.time.LocalDate;

public class NumbersHelperClass {

    public static LocalDate getDateCheck(String date){
        if(date == null){
            return LocalDate.now();
        }else if (date.isEmpty()){
            return LocalDate.now();
        }
        String[] lDate = date.split("-");
        return LocalDate.of(Integer.parseInt(lDate[0]),Integer.parseInt(lDate[1]),Integer.parseInt(lDate[2]));

    }

    public static NumberRows getNumbersRows(NumbersRequest request){
        NumberRows numberRows = new NumberRows();
        numberRows.setNumberOne(request.getNumberOne());
        numberRows.setNumberTwo(request.getNumberTwo());
        numberRows.setNumberThree(request.getNumberThree());
        numberRows.setNumberFour(request.getNumberFour());
        numberRows.setNumberFive(request.getNumberFive());
        numberRows.setNumberSix(request.getNumberSix());
        numberRows.setNumberBonus(request.getNumberBonus());
        return numberRows;
    }
}
