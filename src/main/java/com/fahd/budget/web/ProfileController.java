package com.fahd.budget.web;

import com.fahd.budget.domain.Profile;
import com.fahd.budget.repo.ProfileRepo;
import com.fahd.budget.service.PlannerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin
public class ProfileController {
    private final PlannerService planner;
    private final ProfileRepo repo;
    public ProfileController(PlannerService planner, ProfileRepo repo){
        this.planner = planner; this.repo = repo;
    }

    @GetMapping
    public Profile get(){ return planner.getOrInitProfile(); }

    @PutMapping
    public Profile update(@Valid @RequestBody Profile p){
        p.setId(1L);
        return planner.saveProfile(p);
    }
}
