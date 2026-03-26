package com.example.SafetyNet.controler;

import com.example.SafetyNet.model.MedicalRecords;
import com.example.SafetyNet.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecords")
public class MedicalRecordControler {

    @Autowired
    MedicalRecordService MedicalRecordService;

    @PostMapping
    public MedicalRecords addFireStation(@RequestBody MedicalRecords medicalRecord) {
        return MedicalRecordService.addFireStation(medicalRecord);
    }

    @PutMapping
    public MedicalRecords updateFireStation(@RequestBody MedicalRecords medicalRecord) {
        return MedicalRecordService.updateFireStation(medicalRecord);
    }

    @DeleteMapping
    public MedicalRecords deleteFireStation(@RequestBody MedicalRecords medicalRecord) {
        return MedicalRecordService.deleteFireStation(medicalRecord);
    }
}