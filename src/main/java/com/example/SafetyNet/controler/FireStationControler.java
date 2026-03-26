package com.example.SafetyNet.controler;
import com.example.SafetyNet.model.FireStations;
import com.example.SafetyNet.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firestation")
public class FireStationControler {

    @Autowired
    FireStationService FireStationService;

    @PostMapping
    public FireStations addFireStation(@RequestBody FireStations fireStation) {
        return FireStationService.addFireStation(fireStation);
    }

    @PutMapping
    public FireStations updateFireStation(@RequestBody FireStations fireStation) {
        return FireStationService.updateFireStation(fireStation);
    }

    @DeleteMapping
    public FireStations deleteFireStation(@RequestBody FireStations fireStation) {
        return FireStationService.deleteFireStation(fireStation);
    }
}
