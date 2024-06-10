package com.example.billing.service;


import com.example.billing.model.Transaction;
import com.example.billing.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Override
    public List<Transaction> processFile(MultipartFile file) throws Exception{
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                if (fields.length != 6) {
                    throw new IllegalArgumentException("Invalid file format");
                }

                String id = fields[0];
                LocalDate date = LocalDate.parse(fields[1], DateTimeFormatter.BASIC_ISO_DATE);
                String customerName = fields[2];
                int transactionsCount = Integer.parseInt(fields[3]);
                int freeTransactions = Integer.parseInt(fields[4]);
                BigDecimal chargeOfEachTransaction = new BigDecimal(fields[5]);
                int paidTransactions = transactionsCount - freeTransactions;
                BigDecimal netCharge = chargeOfEachTransaction.multiply(new BigDecimal(paidTransactions));

                BigDecimal big10 = new BigDecimal(0);

                if(chargeOfEachTransaction.compareTo(big10) < 0)
                {
                    throw new IllegalArgumentException("Charge of Transactions cannot be Negative");
                }

                // Check for duplicates
//                Optional<Transaction> existingTransaction = transactionRepository.findByDateAndName(date, customerName);
//                if (existingTransaction.isPresent()) {
//                    //System.out.println("Present!!!!!");
//                    throw new IllegalArgumentException("Duplicate data found for date " + date + " and customer " + customerName);
//                }

                // Check for duplicates
                long duplicateCount = transactionRepository.countByDateAndName(date, customerName);
                if (duplicateCount > 0) {
                    // Log the error message to the console
                    // System.out.println("Duplicate data found for date " + date + " and customer " + customerName);
                    // Throw a custom exception
                    throw new IllegalArgumentException("Duplicate data found for date " + date + " and customer " + customerName);
                }

//                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
//                Date dateNow = new Date();
//                System.out.println(formatter.format(date));

                Transaction transaction = new Transaction();
                transaction.setId(id);
                transaction.setDate(date);
                transaction.setName(customerName);
                transaction.setTransactionsCount(transactionsCount);
                transaction.setFreeTransactions(freeTransactions);
                transaction.setPaidTransactions(paidTransactions);
                transaction.setChargeOfEachTransaction(chargeOfEachTransaction);
                transaction.setNetCharge(netCharge);
//                transaction.setCreatedDate();
                transactionRepository.save(transaction);
                transactions.add(transaction);
            }
            // Save to database
            // transactionRepository.saveAll(transactions);
            return transactions;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        // Save to database
//        transactionRepository.saveAll(transactions);
//        return transactions;
    }
}
