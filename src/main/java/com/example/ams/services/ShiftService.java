package com.example.ams.services;

import com.example.ams.models.Shifts;
import com.example.ams.repo.ShiftsRepo;
import com.example.ams.views.ShiftView;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftService {

    private final ShiftsRepo shiftsRepo;

    public ShiftService(ShiftsRepo shiftsRepo) {
        this.shiftsRepo = shiftsRepo;
    }

    public void createShift(Shifts shift) throws RuntimeException{
        if(!shiftsRepo.findByShiftCode(shift.getShiftCode()).isEmpty()) {
            throw new RuntimeException("Shift already found with the same code!");
        }

        shift.setShiftName(shift.getShiftName() + " ( " + shift.getShiftStart() + " to " + shift.getShiftEnd() + " )");
        shiftsRepo.save(shift);
    }

    public void updateShift(Shifts shift) throws RuntimeException {

        if(shift.getShiftCode() == null || shift.getShiftCode().isEmpty()) {
            throw new RuntimeException("Please provide the shift code!");
        }

        if (shiftsRepo.findByShiftCode(shift.getShiftCode()).isEmpty()) {
            throw new RuntimeException("Shift not found!");
        }

        Shifts curShift = shiftsRepo.findByShiftCode(shift.getShiftCode()).getFirst();

        if(shift.getShiftName() != null && !shift.getShiftName().isEmpty()) {
            String shiftStart = curShift.getShiftStart();
            String shiftEnd = curShift.getShiftEnd();
            if(!shift.getShiftStart().isEmpty()) {
                shiftStart = shift.getShiftStart();
            }
            if (!shift.getShiftEnd().isEmpty()) {
                shiftEnd = shift.getShiftEnd();
            }
            curShift.setShiftName(shift.getShiftName() + " ( " + shiftStart + " to " + shiftEnd + " )");
        }

        if(shift.getShiftStart() != null && !shift.getShiftStart().isEmpty()) {
            curShift.setShiftStart(shift.getShiftStart());
        }

        if(shift.getShiftEnd() != null && !shift.getShiftEnd().isEmpty()) {
            curShift.setShiftEnd(shift.getShiftEnd());
        }

        if(shift.getGraceInTime() != null && !shift.getGraceInTime().isEmpty()) {
            curShift.setGraceInTime(shift.getGraceInTime());
        }

        if(shift.getGraceOutTime() != null && !shift.getGraceOutTime().isEmpty()) {
            curShift.setGraceOutTime(shift.getGraceOutTime());
        }

        shiftsRepo.save(curShift);
    }

    public List<ShiftView> getAllShifts() {
        return shiftsRepo.findByIsActive("Active");
    }
}
