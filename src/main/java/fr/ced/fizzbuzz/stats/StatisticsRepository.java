package fr.ced.fizzbuzz.stats;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends CrudRepository<RequestStatEntity, Long> {

    RequestStatEntity findByRequest(String request);

    @Query(value = "SELECT * FROM REQUEST_STAT ORDER BY hit_number DESC LIMIT 1", nativeQuery = true)
    RequestStatEntity findMaxHitNumber();
}
