package com.spothero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spothero.model.Rate;

@Repository
public interface RateRepository extends CrudRepository<Rate, Long> {
	
	@Query("select r " + 
			"from Rate r " + 
			"where day = ?1 " + 
			"and time_zone = ?2 " + 
			"and start_time <= ?3 " + 
			"and end_time > ?4 " + 
			"and start_time < ?3 " + 
			"and end_time >= ?4")
	List<Rate> findRatesForInputCriteria(String day, String tz, Integer startTime, Integer endTime); 
		
}
