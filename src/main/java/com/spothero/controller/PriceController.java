/**
 * 
 */
package com.spothero.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spothero.request.PriceRequest;
import com.spothero.service.PriceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.undertow.util.BadRequestException;


/**
 * @author nayanankmahajan
 *
 */
@RestController
@Api(value = "Parking Price Rest API", description = "Operations pertaining to calculation of parking price")
public class PriceController {
	
	@Autowired
	PriceService priceService;

	@PostMapping("/price")
	@ApiOperation(value = "Get price of parking based on input start and end date")
    public ResponseEntity calculatePrice(@ApiParam(value = "Input Price Request", required = true) @RequestBody PriceRequest priceRequest) {
		Double result;
		try {
			result = priceService.computePrice(priceRequest);
			return ResponseEntity.status(HttpStatus.OK).body(result);
		} catch (BadRequestException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
        
    }
}
