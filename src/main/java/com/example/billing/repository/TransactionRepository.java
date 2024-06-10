package com.example.billing.repository;



import com.example.billing.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, String> {
    @Query("SELECT t FROM Transaction t WHERE t.date = :date AND t.name = :name")
    Optional<Transaction> findByDateAndName(@Param("date") LocalDate date, @Param("name") String name);

    long countByDateAndName(LocalDate date, String customerName);
}
