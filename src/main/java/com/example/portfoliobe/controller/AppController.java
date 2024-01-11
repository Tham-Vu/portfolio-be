package com.example.portfoliobe.controller;

import com.example.portfoliobe.dto.request.CheckMNPRequest;
import com.example.portfoliobe.dto.request.SendOTPRequest;
import com.example.portfoliobe.dto.request.VerifyOTPRequest;
import com.example.portfoliobe.dto.response.CheckMNPResponse;
import com.example.portfoliobe.dto.response.SendOTPResponse;
import com.example.portfoliobe.dto.response.VerifyOTPResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1")
public class AppController {
    @PostMapping("/credit/check_mnp")
    public ResponseEntity<CheckMNPResponse> checkMnp(@RequestBody CheckMNPRequest checkMNPRequest){
        return ResponseEntity.ok().body(new CheckMNPResponse());
    }
    @PostMapping("/credit/otp/send")
    public ResponseEntity<SendOTPResponse> sendOtp (@RequestBody SendOTPRequest sendOTPRequest){
        return ResponseEntity.ok().body(new SendOTPResponse());
    }
    @PostMapping("/credit/otp/verify")
    public ResponseEntity<VerifyOTPResponse> verifyOtp (@RequestBody VerifyOTPRequest request){
        return ResponseEntity.ok().body(new VerifyOTPResponse());
    }
}
