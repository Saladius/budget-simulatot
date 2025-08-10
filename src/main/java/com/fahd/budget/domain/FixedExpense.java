package com.fahd.budget.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class FixedExpense {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ExpensePeriod period = ExpensePeriod.MONTHLY;

    @Min(0)
    private double amount;

    public FixedExpense() {}
    public FixedExpense(String name, ExpensePeriod period, double amount) {
        this.name = name;
        this.period = period;
        this.amount = amount;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ExpensePeriod getPeriod() { return period; }
    public void setPeriod(ExpensePeriod period) { this.period = period; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
