/**
 * 
 */
package com.spothero.service;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.spothero.model.Rate;
import com.spothero.repository.RateRepository;
import com.spothero.request.PriceRequest;

import io.undertow.util.BadRequestException;


/**
 * @author nayanankmahajan
 *
 */
@Service
public class PriceService {
	
RateRepository rateRepository;
	
	@Autowired
    public PriceService(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }
	
	private DateTimeFormatter parser2 = ISODateTimeFormat.dateTimeNoMillis();
	private Map<Integer, String> dayMap = new HashMap<Integer, String>() {{
        put(1, "mon");
        put(2, "tues");
        put(3, "wed");
        put(4, "thurs");
        put(5, "fri");
        put(6, "sat");
        put(7, "sun");
    }};
	
	public Double computePrice(PriceRequest priceRequest) throws BadRequestException {
		
		validatePriceRequest(priceRequest);
		String day = dayMap.get( parser2.parseDateTime(priceRequest.getStartDateTime()).getDayOfWeek());
		String tz = parser2.parseDateTime(priceRequest.getStartDateTime()).getZone().toString();		
		Integer st = (parser2.parseDateTime(priceRequest.getStartDateTime()).getHourOfDay() * 100) + parser2.parseDateTime(priceRequest.getStartDateTime()).getMinuteOfHour();
		Integer et = (parser2.parseDateTime(priceRequest.getEndDateTime()).getHourOfDay() * 100) + parser2.parseDateTime(priceRequest.getEndDateTime()).getMinuteOfHour();

		List<Rate> result = rateRepository.findRatesForInputCriteria(day, tz, st, et);
		if(result.size() == 0) {
			throw new BadRequestException("Unavailable");
		} else if(result.size() > 1){
			throw new BadRequestException("Multiple Rates not supported");
		}
		
		return result.get(0).getPrice();
	}
	
	private void validatePriceRequest(PriceRequest priceRequest) {
		Preconditions.checkArgument(priceRequest != null, "Price Request Not Present");
		Preconditions.checkArgument(priceRequest.getStartDateTime() != null, "Start Date Time must be present");
		Preconditions.checkArgument(priceRequest.getEndDateTime() != null, "End Date Time must be present");
		
		DateTime startDateTime = parser2.parseDateTime(priceRequest.getStartDateTime());
		DateTime endDateTime = parser2.parseDateTime(priceRequest.getEndDateTime());
		
		int daysBetweenStartAndEnd = Days.daysBetween(new LocalDate(startDateTime), new LocalDate(endDateTime)).getDays();
		Preconditions.checkArgument(daysBetweenStartAndEnd == 0, "Rate does not apply to multiple parking days");
	}

	private static String getTimeZone(ZonedDateTime zonedDateTime) {

        String timezone = TimeZone.getTimeZone(zonedDateTime.getZone()).getAvailableIDs()[0];
        System.out.println(timezone);
        return timezone;
    }

}
