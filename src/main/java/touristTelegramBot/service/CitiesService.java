package touristTelegramBot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import touristTelegramBot.model.Cities;
import touristTelegramBot.repo.CitiesRepository;

@Service
public class CitiesService {

    @Autowired
    CitiesRepository citiesRepository;

    public Cities updateCity( Long id, Cities updateCity) {
        Cities city = citiesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("not found with such id"));
        city.setName(updateCity.getName());
        city.setMessage(updateCity.getMessage());

        Cities updatedCity = citiesRepository.save(city);

        //        citiesRepository.update(id, updateCity.getName(), updateCity.getMessage());
        return updatedCity;
    }
}
