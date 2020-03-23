package touristTelegramBot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import touristTelegramBot.model.Cities;
import touristTelegramBot.repo.CitiesRepository;
import touristTelegramBot.service.CitiesService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CitiesController {

    @Autowired
    private CitiesRepository citiesRepository;

    @Autowired
    CitiesService citiesService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Cities>> getAllCities() {
        return ResponseEntity.ok(citiesRepository.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity postCity(@Valid @RequestBody Cities city) {
        return ResponseEntity.ok(citiesRepository.save(city));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Cities> getCityById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(citiesRepository.findById(id).orElseGet(null));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Cities> updateCity(@PathVariable(value = "id") Long id,
                                             @RequestBody Cities updateCity) {
        return ResponseEntity.ok(citiesService.updateCity(id, updateCity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable(value = "id") Long id) {
        Cities city = citiesRepository.findById(id).get();

        citiesRepository.delete(city);

        return ResponseEntity.ok().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}
