package com.fahd.budget.repo;
import com.fahd.budget.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProfileRepo extends JpaRepository<Profile, Long> {}
