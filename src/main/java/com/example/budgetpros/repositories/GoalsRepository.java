package com.example.budgetpros.repositories;

import com.example.budgetpros.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalsRepository extends JpaRepository<Goal, Long> {
}
