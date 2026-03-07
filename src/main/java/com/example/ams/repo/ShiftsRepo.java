package com.example.ams.repo;

import com.example.ams.models.Shifts;
import com.example.ams.views.ShiftView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftsRepo extends JpaRepository<Shifts, Long> {

    public List<Shifts> findAll();
    public List<Shifts> findByShiftCode(String ShiftCode);
    public List<ShiftView> findByIsActive(String isActive);
}
