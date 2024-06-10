package com.example.billing.service;

import com.example.billing.model.Transaction;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TransactionService {

    List<Transaction> processFile(MultipartFile file) throws Exception;
}
