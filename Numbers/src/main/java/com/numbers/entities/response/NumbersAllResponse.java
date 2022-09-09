package com.numbers.entities.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@RequiredArgsConstructor
public class NumbersAllResponse implements Serializable {
    //Must change this coz of its return
    private Object[] data;

    public NumbersAllResponse(Object[] data) {
        this.data = data;
    }

    public final void getThisMethod(){

    }
}
