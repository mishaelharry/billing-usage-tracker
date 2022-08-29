package com.usage.tracker.app.repository;

import com.usage.tracker.app.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query(value = "SELECT * FROM prices p WHERE p.max > ?1 AND p.min < ?2", nativeQuery = true)
    Price findByMinGreaterThanAndMaxLessThan(Integer min, Integer max);

}
