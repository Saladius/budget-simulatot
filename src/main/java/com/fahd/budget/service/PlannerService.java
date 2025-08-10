package com.fahd.budget.service;

import com.fahd.budget.domain.ExpensePeriod;
import com.fahd.budget.domain.FixedExpense;
import com.fahd.budget.domain.Profile;
import com.fahd.budget.dto.PlanResponse;
import com.fahd.budget.repo.FixedExpenseRepo;
import com.fahd.budget.repo.ProfileRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlannerService {

    private final ProfileRepo profileRepo;
    private final FixedExpenseRepo fixedExpenseRepo;

    public PlannerService(ProfileRepo profileRepo, FixedExpenseRepo fixedExpenseRepo) {
        this.profileRepo = profileRepo;
        this.fixedExpenseRepo = fixedExpenseRepo;
    }

    public Profile getOrInitProfile() {
        return profileRepo.findById(1L).orElseGet(() -> {
            Profile p = new Profile();
            return profileRepo.save(p);
        });
    }

    public Profile saveProfile(Profile p) {
        p.setMonthlyIncome(Math.max(0, p.getMonthlyIncome()));
        p.setGoalAmount(Math.max(0, p.getGoalAmount()));
        p.setGoalMonths(Math.max(1, p.getGoalMonths()));
        p.setGroceries(Math.max(0, p.getGroceries()));
        p = profileRepo.save(p);
        return p;
    }

    public double monthlyAmount(FixedExpense e) {
        return e.getPeriod() == ExpensePeriod.MONTHLY ? e.getAmount() : e.getAmount() / 12.0;
    }

    public double fixedTotal() {
        return fixedExpenseRepo.findAll().stream().mapToDouble(this::monthlyAmount).sum();
    }

    public PlanResponse computePlan(Double overrideIncome, Double overrideGoalAmount, Integer overrideGoalMonths, Double overrideGroceries) {
        Profile p = getOrInitProfile();
        double income = overrideIncome != null ? overrideIncome : p.getMonthlyIncome();
        double goalAmount = overrideGoalAmount != null ? overrideGoalAmount : p.getGoalAmount();
        int goalMonths = overrideGoalMonths != null ? Math.max(1, overrideGoalMonths) : p.getGoalMonths();
        double groceries = overrideGroceries != null ? overrideGroceries : p.getGroceries();

        double savingsRequired = goalMonths > 0 ? goalAmount / goalMonths : 0.0;
        double fixed = fixedTotal();

        // Rules: allocate emergency buffer ~3% of income by default
        double emergency = income * 0.03;

        // Whatever remains after fixed + groceries + emergency goes to leisure and savings.
        double remaining = income - fixed - groceries - emergency;

        double leisure = Math.max(0, remaining * 0.35); // 35% of what's left for leisure by default
        double savingsAchieved = Math.max(0, remaining - leisure);

        // If savingsAchieved < savingsRequired, tighten leisure first
        if (savingsAchieved < savingsRequired) {
            double deficit = savingsRequired - savingsAchieved;
            double reduceLeisure = Math.min(leisure, deficit);
            leisure -= reduceLeisure;
            savingsAchieved += reduceLeisure;
        }

        // If still not enough, suggest reduction from groceries (soft cap)
        if (savingsAchieved < savingsRequired) {
            double deficit = savingsRequired - savingsAchieved;
            double reduceGroceries = Math.min(Math.max(0, groceries - 4000), deficit); // don't recommend below 4000
            groceries -= reduceGroceries;
            savingsAchieved += reduceGroceries;
        }

        // leftover can be negative if income < fixed+groceries+emergency
        double leftover = income - fixed - groceries - leisure - emergency - savingsAchieved;

        return new PlanResponse(income, fixed, groceries, leisure, emergency, savingsRequired, savingsAchieved, leftover);
    }
}
