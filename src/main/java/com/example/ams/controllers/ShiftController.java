package com.example.ams.controllers;

import com.example.ams.models.Shifts;
import com.example.ams.services.ShiftService;
import com.example.ams.views.ShiftView;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ShiftController {

    private final ShiftService service;

    public ShiftController(ShiftService service) {
        this.service = service;
    }

    @PostMapping("/shifts")
    public ResponseEntity<HashMap<String,String>> createShift (@RequestBody Shifts shift) {
        try{
            service.createShift(shift);
            return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<>(Map.of(
                    "status","1",
                    "message","Shift created successfully"
            )));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<>(Map.of(
                    "status","0",
                    "message",e.getMessage()
            )));
        }
    }

    @PutMapping("/shifts")
    public ResponseEntity<HashMap<String,String>> updateShift(@RequestBody Shifts shift) {
        try{
            service.updateShift(shift);
            return ResponseEntity.status(HttpStatus.OK).body(new HashMap<>(Map.of(
                    "status","1",
                    "message","Shift updated successfully"
            )));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<>(Map.of(
                    "status","0",
                    "message",e.getMessage()
            )));
        }
    }

    @GetMapping("/shifts")
    public ResponseEntity<List<ShiftView>> getShifts() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllShifts());
    }
}
