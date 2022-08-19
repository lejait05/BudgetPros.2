package com.example.budgetpros.repositories;

import com.example.budgetpros.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
 List<Transaction> findByUserId(Long id);
}
