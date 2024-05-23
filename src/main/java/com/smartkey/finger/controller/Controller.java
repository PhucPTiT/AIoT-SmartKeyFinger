package com.smartkey.finger.controller;

import com.smartkey.finger.entity.Account;
import com.smartkey.finger.entity.Log;
import com.smartkey.finger.entity.UserFinger;
import com.smartkey.finger.repository.AccountRepository;
import com.smartkey.finger.repository.LogRepository;
import com.smartkey.finger.repository.UserFingerRepository;
import com.smartkey.finger.service.impl.AccountService;
import com.smartkey.finger.service.impl.LogService;
import com.smartkey.finger.service.impl.UserFingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class Controller {
    @Autowired
    private LogService logService;

    @Autowired
    private UserFingerService userFingerService;


    @Autowired
    private UserFingerRepository userFingerRepository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;


   @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account) {
        try {
            return ResponseEntity.ok(accountService.login(account));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Account account) {
        try {
            return ResponseEntity.ok(accountService.signup(account));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/userfinger/all")
    public ResponseEntity<?> getAllUserFinger() {
        return ResponseEntity.ok(userFingerRepository.findAll());
    }

    @GetMapping("/log/all")
    public ResponseEntity<?> getAllLog() {
        return ResponseEntity.ok(logRepository.findAll());
    }

    @PostMapping("/userfinger")
    public ResponseEntity<?> addUserFinger(@RequestBody UserFinger userFinger) {
        try {
            return ResponseEntity.ok(userFingerService.addUserFinger(userFinger));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/log/{fingerId}")
    public ResponseEntity<?> addLog(@RequestBody Log log, @PathVariable String fingerId) {
        try {
            return ResponseEntity.ok(logService.addLog(log, fingerId));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
