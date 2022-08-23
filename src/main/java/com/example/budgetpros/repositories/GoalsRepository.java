package com.example.budgetpros.repositories;

import com.example.budgetpros.model.Goal;
import com.example.budgetpros.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GoalsRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUserId(Long id);
}
