/**
 * 
 */
package com.spothero.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.spothero.model.Rate;
import com.spothero.repository.RateRepository;
import com.spothero.request.PriceRequest;

import io.undertow.util.BadRequestException;

/**
 * @author nayanankmahajan
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PriceServiceTest {
	
	PriceService priceService;
	
	RateRepository rateRepository;
	
	@Before
    public void init() {
		rateRepository = mock(RateRepository.class);
		priceService = new PriceService(rateRepository);
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void computePriceWhenInputIsValid() throws BadRequestException {
		PriceRequest priceRequest = new PriceRequest();
		priceRequest.setStartDateTime("2019-04-01T01:00:00-05:00");
		priceRequest.setEndDateTime("2019-04-01T10:30:00-05:00");
		
		Double expectedPrice = 1500.0;
		Rate rate = new Rate();
		rate.setPrice(expectedPrice);
		List<Rate> actualResult = new ArrayList();
		actualResult.add(rate);
		
		when(rateRepository.
				findRatesForInputCriteria(anyString(), anyString(), any(), any()))
		.thenReturn(actualResult);
		assertEquals("Price does not match", expectedPrice, priceService.computePrice(priceRequest));
	}
	
	@Test(expected = BadRequestException.class)
	public void computePriceWhenNoRateExist_thenThrowException() throws BadRequestException {
		PriceRequest priceRequest = new PriceRequest();
		priceRequest.setStartDateTime("2019-04-01T01:00:00-05:00");
		priceRequest.setEndDateTime("2019-04-01T10:30:00-05:00");
		
		when(rateRepository.
				findRatesForInputCriteria(anyString(), anyString(), any(), any()))
		.thenReturn(Collections.emptyList());
		priceService.computePrice(priceRequest);
	}
	
	@Test(expected = BadRequestException.class)
	public void computePriceWhenMultipleRateExist_thenThrowException() throws BadRequestException {
		PriceRequest priceRequest = new PriceRequest();
		priceRequest.setStartDateTime("2019-04-01T01:00:00-05:00");
		priceRequest.setEndDateTime("2019-04-01T10:30:00-05:00");
		
		Rate rate1 = new Rate();
		Rate rate2 = new Rate();
		List<Rate> rateResult = new ArrayList();
		rateResult.add(rate1);
		rateResult.add(rate2);
		
		when(rateRepository.
				findRatesForInputCriteria(anyString(), anyString(), any(), any()))
		.thenReturn(rateResult);
		priceService.computePrice(priceRequest);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void computePriceWithNullRequest_thenThrowException() throws BadRequestException {
		priceService.computePrice(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void computePriceWithNullStartDateTime_thenThrowException() throws BadRequestException {
		PriceRequest priceRequest = new PriceRequest();
		priceRequest.setEndDateTime("2019-04-01T10:30:00-05:00");
		priceService.computePrice(priceRequest);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void computePriceWithNullEndDateTime_thenThrowException() throws BadRequestException {
		PriceRequest priceRequest = new PriceRequest();
		priceRequest.setStartDateTime("2019-04-01T10:30:00-05:00");
		priceService.computePrice(priceRequest);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void computePriceWithMultipleDaysAsInput_thenThrowException() throws BadRequestException {
		PriceRequest priceRequest = new PriceRequest();
		priceRequest.setStartDateTime("2019-04-01T10:30:00-05:00");
		priceRequest.setEndDateTime("2019-04-03T10:30:00-05:00");
		priceService.computePrice(priceRequest);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void computePriceWithInvalidStartDate_thenThrowException() throws BadRequestException {
		PriceRequest priceRequest = new PriceRequest();
		priceRequest.setStartDateTime("Blah");
		priceRequest.setEndDateTime("2019-04-03T10:30:00-05:00");
		priceService.computePrice(priceRequest);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void computePriceWithInvalidEndDate_thenThrowException() throws BadRequestException {
		PriceRequest priceRequest = new PriceRequest();
		priceRequest.setStartDateTime("2019-04-01T10:30:00-05:00");
		priceRequest.setEndDateTime("Blah");
		priceService.computePrice(priceRequest);
	}

}
