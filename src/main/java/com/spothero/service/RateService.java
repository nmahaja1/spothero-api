/**
 * 
 */
package com.spothero.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spothero.model.Rate;
import com.spothero.repository.RateRepository;
import com.spothero.request.RateRequest;

/**
 * @author nayanankmahajan
 *
 */
@Service
public class RateService {
	
	@Autowired
	RateRepository rateRepository;

	public void createRate(List<RateRequest> rateRequestList) {
		//Business logic
		System.out.println("In service layer");
		rateRequestList.forEach(rateRequest -> {
			List<Rate> entities = new ArrayList();
			
			Arrays.asList(rateRequest.getDays().split(",")).forEach(day -> {
				Rate entity = new Rate();
				entity.setDay((String) day);
				String[] timeRange = rateRequest.getTimes().split("-");
				entity.setStartTime(Integer.parseInt(timeRange[0]));
				entity.setEndTime(Integer.parseInt(timeRange[1]));
				entity.setTimeZone(rateRequest.getTz());
				entity.setPrice(rateRequest.getPrice());
				
				entities.add(entity);
			});
			
			rateRepository.saveAll(entities);
		});
	}
	
	public void updateRate(List<RateRequest> rateRequestList) {
		//Delete
		rateRepository.deleteAll();
		//Create
		createRate(rateRequestList);
	}
	
	public List<Rate> getAllRates() {
		return (List<Rate>) rateRepository.findAll();
	}
}
