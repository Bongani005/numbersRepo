package com.numbers.controller;

import com.numbers.entities.request.NumbersRequest;
import com.numbers.entities.response.LunchTimeResponse;
import com.numbers.entities.response.NumbersAllResponse;
import com.numbers.entities.response.TeaTimeResponse;
import com.numbers.service.LunchTimeService;
import com.numbers.service.TeaTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/results/uk49s")
public class NumbersResultsController {

    @Autowired
    private LunchTimeService lunchTimeService;

    @Autowired
    private TeaTimeService teaTimeService;

    @PostMapping(value = "/saveLunchTime", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public LunchTimeResponse saveLunchTime(@RequestBody NumbersRequest request){
        return new LunchTimeResponse(lunchTimeService.saveLunchTime(request));
    }

    @PostMapping(value = "/saveTeaTime", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TeaTimeResponse saveTeaTime(@RequestBody NumbersRequest request){
        return new TeaTimeResponse(teaTimeService.saveTeatime(request));
    }

    @GetMapping(value = "/getAllResults", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<NumbersAllResponse> getAllResults(){
        List<NumbersAllResponse> numbersAllResponses = new ArrayList<>();
        Object[] objects = new Object[2];
        objects[0] = lunchTimeService.getAllLunchTimeResults();
        objects[1] = teaTimeService.getAllTeaTimeResults();
        numbersAllResponses.add(new NumbersAllResponse(objects));
        return numbersAllResponses;
    }

    @GetMapping(value = "/getAllTeaTimeResults", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeaTimeResponse> getAllTeaTimeResults(){
        List<TeaTimeResponse> teaTimeResponseList = new ArrayList<>();
        teaTimeService.getAllTeaTimeResults().forEach(teaTime -> {
            teaTimeResponseList.add(new TeaTimeResponse(teaTime));
        });
        return teaTimeResponseList;
    }

    @GetMapping(value = "/getAllLunchTimeResults", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LunchTimeResponse> getAllLunchTimeResults(){
        List<LunchTimeResponse> lunchTimeResponseList = new ArrayList<>();
        lunchTimeService.getAllLunchTimeResults().forEach(lunchTime -> {
            lunchTimeResponseList.add(new LunchTimeResponse(lunchTime));
        });
        return lunchTimeResponseList;
    }

    @DeleteMapping(value = "/")
    public void deleteResultsLessThan27Days(){
        teaTimeService.deleteResultsLessThan27Days();
        lunchTimeService.deleteResultsLessThan27Days();
    }

    @GetMapping(value = "/ping")
    public String ping(){
        return "Results Number Api Connected at Date:  "+ LocalDateTime.now();
    }
}
