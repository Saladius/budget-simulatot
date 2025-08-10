package com.fahd.budget.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
public class Profile {
    @Id
    private Long id = 1L;

    @Min(0)
    private double monthlyIncome = 0.0;

    @Min(0)
    private double goalAmount = 0.0;

    @Min(1)
    private int goalMonths = 24;

    // default groceries envelope
    @Min(0)
    private double groceries = 6000.0;

    public Profile() {}
    public Long getId() { return id; }
    public double getMonthlyIncome() { return monthlyIncome; }
    public void setMonthlyIncome(double monthlyIncome) { this.monthlyIncome = monthlyIncome; }
    public double getGoalAmount() { return goalAmount; }
    public void setGoalAmount(double goalAmount) { this.goalAmount = goalAmount; }
    public int getGoalMonths() { return goalMonths; }
    public void setGoalMonths(int goalMonths) { this.goalMonths = goalMonths; }
    public double getGroceries() { return groceries; }
    public void setGroceries(double groceries) { this.groceries = groceries; }
}
