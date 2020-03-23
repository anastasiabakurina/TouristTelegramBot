package touristTelegramBot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import touristTelegramBot.model.Cities;

import java.util.List;

@Repository
public interface CitiesRepository extends JpaRepository<Cities, Long> {
    @Query("select lower(c.name) from Cities c")
    List<String> findCitiesNames();

    @Query("select lower(c.message) from Cities c where lower(c.name) = ?1 ")
    String messageOfCity(String city);

//    @Modifying
//    @Query("update Cities c set c.name = ?2, c.message = ?3 where c.id = ?1")
//    void update(Long id, String name, String mes);
}
