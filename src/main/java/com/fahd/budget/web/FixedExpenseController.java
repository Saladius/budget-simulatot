package com.fahd.budget.web;

import com.fahd.budget.domain.FixedExpense;
import com.fahd.budget.repo.FixedExpenseRepo;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fixed-expenses")
@CrossOrigin
public class FixedExpenseController {
    private final FixedExpenseRepo repo;
    public FixedExpenseController(FixedExpenseRepo repo){ this.repo = repo; }

    @GetMapping
    public List<FixedExpense> all(){ return repo.findAll(); }

    @PostMapping
    public FixedExpense create(@Valid @RequestBody FixedExpense e){ return repo.save(e); }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(!repo.existsById(id)) return ResponseEntity.notFound().build();
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
