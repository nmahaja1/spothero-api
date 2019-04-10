/**
 * 
 */
package com.spothero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spothero.request.RateRequest;
import com.spothero.service.RateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author nayanankmahajan
 *
 */
@RestController
@Api(value = "Parking Rate Rest API", description = "Operations pertaining to creation and update of parking rate")
public class RateController {
	
	@Autowired
	RateService rateService;
	
	@PostMapping("/rate")
	@ApiOperation(value = "Create Rate")
	public void createRate(@ApiParam(value = "List of Input Rates", required = true) @RequestBody List<RateRequest> rateRequestList) {
		System.out.println("In controller layer");
		rateService.createRate(rateRequestList);
	}
	
	@PutMapping("/rate")
	@ApiOperation(value = "Update Rate")
	public void updateRate(@RequestBody List<RateRequest> rateRequestList) {
		rateService.updateRate(rateRequestList);
	}
	
	@GetMapping("/rate")
	public ResponseEntity getAllRates() {
		return ResponseEntity.status(HttpStatus.OK).body(rateService.getAllRates());
	}

}
