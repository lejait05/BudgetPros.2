package com.example.budgetpros;

import com.example.budgetpros.model.Transaction_Types;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionTypesRepo extends JpaRepository<Transaction_Types, Long> {

}
