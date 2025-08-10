package com.fahd.budget.repo;
import com.fahd.budget.domain.FixedExpense;
import org.springframework.data.jpa.repository.JpaRepository;
public interface FixedExpenseRepo extends JpaRepository<FixedExpense, Long> {}
