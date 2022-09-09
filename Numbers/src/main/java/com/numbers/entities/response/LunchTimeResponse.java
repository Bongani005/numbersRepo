package com.numbers.entities.response;

import com.numbers.entities.LunchTime;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
@RequiredArgsConstructor
public class LunchTimeResponse implements Serializable {
    private LunchTime lunchTime;

    public LunchTimeResponse(LunchTime lunchTime) {
        this.lunchTime = lunchTime;
    }
}
