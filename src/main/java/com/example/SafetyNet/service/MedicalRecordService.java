package com.example.SafetyNet.service;

import com.example.SafetyNet.model.MedicalRecord;
import com.example.SafetyNet.repository.FireStationRepository;
import com.example.SafetyNet.repository.MedicalRecordRepository;
import com.example.SafetyNet.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {

    private final PersonRepository personRepository;
    private final FireStationRepository fireStationRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordService(PersonRepository personRepository, FireStationRepository fireStationRepository, MedicalRecordRepository medicalRecordRepository) {
        this.fireStationRepository = fireStationRepository;
        this.personRepository = personRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public MedicalRecord addFireStation(MedicalRecord medicalRecord) {
        return null;
    }

    public MedicalRecord updateFireStation(MedicalRecord medicalRecord) {
        return null;
    }

    public MedicalRecord deleteFireStation(MedicalRecord medicalRecord) {
        return null;
    }
}
