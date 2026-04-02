package com.example.SafetyNet.controler;
import com.example.SafetyNet.model.FireStation;
import com.example.SafetyNet.service.FireStationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FireStationControler {

    private final FireStationService fireStationService;

    public FireStationControler(FireStationService fireStationService) {
        this.fireStationService = fireStationService;
    }

    /*@PostMapping
    public FireStations addFireStation(@RequestBody FireStations fireStation) {
        return fireStationService.addFireStation(fireStation);
    }

    @PutMapping
    public FireStations updateFireStation(@RequestBody FireStations fireStation) {
        return fireStationService.updateFireStation(fireStation);
    }

    @DeleteMapping
    public FireStations deleteFireStation(@RequestBody FireStations fireStation) {
        return fireStationService.deleteFireStation(fireStation);
    }*/

    @GetMapping("fire")
    public List<String> fire(@RequestParam(name = "address") String fireStation){
        return fireStationService.fire(fireStation);
    }

    @GetMapping("phoneAlert")
    public List<String> phoneAlert(@RequestParam(name = "firestation_number") String fireStation){
        return fireStationService.phoneAlert(fireStation);
    }

    @GetMapping("firestation")
    public List<String> firestation(@RequestParam(name = "station_number") String station_number){
        return fireStationService.stationNumber(station_number);
    }
}
