package com.example.billing.controller;


import com.example.billing.exception.CustomException;
import com.example.billing.model.ErrorResponse;
import com.example.billing.model.Transaction;

import com.example.billing.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;


@RestController
public class TransactionController{

    private TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping("/upload")
    public ResponseEntity<List<Transaction>> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
//        try {
//            List<Transaction> transactions = transactionService.processFile(file);
//            return ResponseEntity.ok(transactions);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(null);
//        }

        List<Transaction> transactions = transactionService.processFile(file);
        return ResponseEntity.ok(transactions);
    }




}
