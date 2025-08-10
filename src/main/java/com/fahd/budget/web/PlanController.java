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
    public PlanResponse get(@RequestParam(required = false) Double income,
                            @RequestParam(required = false) Double goalAmount,
                            @RequestParam(required = false) Integer goalMonths,
                            @RequestParam(required = false) Double groceries){
        return planner.computePlan(income, goalAmount, goalMonths, groceries);
    }
}
