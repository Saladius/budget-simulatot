package com.fahd.budget.dto;

public record PlanResponse(
    double income,
    double fixedTotal,
    double groceries,
    double leisure,
    double emergencyBuffer,
    double savingsRequired,
    double savingsAchieved,
    double leftover
) {}
