package com.example.SafetyNet.controler;
import com.example.SafetyNet.model.FireStations;
import com.example.SafetyNet.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/firestation")
public class FireStationControler {

    //@Autowired
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

    /*@GetMapping("fire")
    public FireStations fire(@RequestParam(name = "address") String fireStation){
        return fireStationService.fire(fireStation);
    }*/

    @GetMapping("phoneAlert")
    public FireStations phoneAlert(@RequestParam(name = "firestation_number") String fireStation){
        return fireStationService.phoneAlert(fireStation);
    }
}
