package com.example.billing.model;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


@Data@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    private String id;
    private LocalDate date;
    private String name;
    private int transactionsCount;
    private int freeTransactions;
    private int paidTransactions;
    private BigDecimal chargeOfEachTransaction;
    private BigDecimal netCharge;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    public Transaction(String id, LocalDate date, String name, int transactionsCount, int freeTransactions, int paidTransactions, BigDecimal chargeOfEachTransaction, BigDecimal netCharge) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.transactionsCount = transactionsCount;
        this.freeTransactions = freeTransactions;
        this.paidTransactions = paidTransactions;
        this.chargeOfEachTransaction = chargeOfEachTransaction;
        this.netCharge = netCharge;
//        this.createdDate = createdDate;
    }
// Getters and Setters
}
