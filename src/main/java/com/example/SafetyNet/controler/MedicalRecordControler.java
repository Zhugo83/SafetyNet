package com.example.SafetyNet.controler;

import com.example.SafetyNet.service.MedicalRecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalRecords")
public class MedicalRecordControler {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordControler(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    /*@PostMapping
    public MedicalRecords addFireStation(@RequestBody MedicalRecords medicalRecord) {
        return medicalRecordService.addFireStation(medicalRecord);
    }

    @PutMapping
    public MedicalRecords updateFireStation(@RequestBody MedicalRecords medicalRecord) {
        return medicalRecordService.updateFireStation(medicalRecord);
    }

    @DeleteMapping
    public MedicalRecords deleteFireStation(@RequestBody MedicalRecords medicalRecord) {
        return medicalRecordService.deleteFireStation(medicalRecord);
    }*/
}