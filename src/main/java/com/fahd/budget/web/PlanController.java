package com.fahd.budget.web;

import com.fahd.budget.dto.PlanResponse;
import com.fahd.budget.service.PlannerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/plan")
@CrossOrigin
public class PlanController {
    private final PlannerService planner;
    public PlanController(PlannerService planner){ this.planner = planner; }

    @GetMapping
    public PlanResponse get(
            @RequestParam(name = "income", required = false) Double income,
            @RequestParam(name = "goalAmount", required = false) Double goalAmount,
            @RequestParam(name = "goalMonths", required = false) Integer goalMonths,
            @RequestParam(name = "groceries", required = false) Double groceries
    ) {
        return planner.computePlan(income, goalAmount, goalMonths, groceries);
    }
}
