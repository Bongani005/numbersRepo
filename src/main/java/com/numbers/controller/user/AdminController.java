package com.numbers.controller.user;

import com.numbers.service.LoginHistoryService;
import com.numbers.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

    @Autowired
    private UserServices services;

    @Autowired
    private LoginHistoryService loginHistoryService;

    @PatchMapping(value = "/{verificationCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VerificationResponse> verifyUser(@PathVariable(value = "verificationCode") String verificationCode){
        return new ResponseEntity<>(services.activateUser(verificationCode), HttpStatus.ACCEPTED);
    }


    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SingleUserResponse>> getAllUsers(){
        return new ResponseEntity<>(services.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/getAllHistory", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LoginHistoryResponse>> getAllHistory(){
        return new ResponseEntity<>(loginHistoryService.getAllHistory(), HttpStatus.OK);
    }
}
