package com.example.ams.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Shifts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "shift_code")
    private String shiftCode;

    @Column(name = "shift_name")
    private String shiftName;

    @Column(name = "shift_start")
    private String shiftStart;

    @Column(name = "shift_end")
    private String shiftEnd;

    @Column(name = "grace_in_time")
    private String graceInTime;

    @Column(name = "grace_out_time")
    private String graceOutTime;

    @Column(name = "is_active")
    private String isActive;
}
