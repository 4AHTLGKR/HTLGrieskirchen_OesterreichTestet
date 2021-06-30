package at.htlgkr.htltestet.controller;

import at.htlgkr.htltestet.data.ScreeningStation;
import at.htlgkr.htltestet.data.ScreeningStationDto;
import at.htlgkr.htltestet.data.ScreeningStationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin
public class ScreeningStationController {

private final ScreeningStationRepository screeningStationRepository;

public ScreeningStationController(ScreeningStationRepository screeningStationRepository) {this.screeningStationRepository = screeningStationRepository;}

    @GetMapping("/screeningStation")
    @ResponseBody
    List<ScreeningStation> getScreeningStation() {
        return screeningStationRepository.findAll();
    }

    @GetMapping("/screeningStation/{screeningStationId}")
    @ResponseBody
    Optional<ScreeningStation> getScreeningStation(@PathVariable Integer scrId) {
        return screeningStationRepository.findById(scrId);
    }

    @PostMapping("/serviceBackend/employees")
    @ResponseBody
    ScreeningStation postEmployee(@RequestBody ScreeningStationDto screeningStation) {
        return screeningStationRepository.save(screeningStation.toScreeningStation());
    }
}
