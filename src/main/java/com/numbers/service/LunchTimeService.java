package com.numbers.service;

import com.numbers.entities.LunchTime;
import com.numbers.entities.request.NumbersRequest;
import com.numbers.repository.LunchTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.numbers.service.NumbersHelperClass.getDateCheck;
import static com.numbers.service.NumbersHelperClass.getNumbersRows;

@Service
public class LunchTimeService {

    @Autowired
    public LunchTimeRepository repository;

    public LunchTime saveLunchTime(NumbersRequest request){
        LunchTime lunchTime = new LunchTime();
        lunchTime.setResultDate(getDateCheck(request.getResultDate()));
        lunchTime.setNumberRows(getNumbersRows(request));
        repository.save(lunchTime);
        return lunchTime;
    }

    @CachePut(value = "LunchTime")
    public List<LunchTime> getAllLunchTimeResults(){
        return new ArrayList<>(repository.findAll());
    }

    public void deleteResultsLessThan27Days(){
        LocalDate localDate = LocalDate.now();
        localDate = localDate.minusDays(27);
        repository.deleteByDateLessThan(localDate);
    }

}
