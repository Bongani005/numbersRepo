package com.numbers.service;

import com.numbers.entities.TeaTime;
import com.numbers.entities.request.NumbersRequest;
import com.numbers.repository.TeaTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.numbers.service.NumbersHelperClass.getDateCheck;
import static com.numbers.service.NumbersHelperClass.getNumbersRows;

@Service
public class TeaTimeService {

    @Autowired
    public TeaTimeRepository repository;

    public TeaTime saveTeatime(NumbersRequest request){
        TeaTime teaTime = new TeaTime();
        teaTime.setResultDate(getDateCheck(request.getResultDate()));
        teaTime.setNumberRows(getNumbersRows(request));
        repository.save(teaTime);
        return teaTime;
    }

    @CachePut(value = "TeaTime")
    public List<TeaTime> getAllTeaTimeResults(){
        return new ArrayList<>(repository.findAll());
    }

    public void deleteResultsLessThan27Days(){
        LocalDate localDate = LocalDate.now();
        localDate = localDate.minusDays(27);
        repository.deleteByDateLessThan(localDate);
    }
}
