package com.numbers.entities.response;

import com.numbers.entities.TeaTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
@ToString
public class TeaTimeResponse implements Serializable {
    private TeaTime teaTime;

    public TeaTimeResponse(TeaTime teaTime) {
        this.teaTime = teaTime;
    }
}
