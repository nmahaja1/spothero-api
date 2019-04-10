/**
 * 
 */
package com.spothero.request;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * @author nayanankmahajan
 *
 */
public class PriceRequest {

	String startDateTime;
	String endDateTime;
	/**
	 * @return the startDateTime
	 */
	public String getStartDateTime() {
		return startDateTime;
	}
	/**
	 * @param startDateTime the startDateTime to set
	 */
	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}
	/**
	 * @return the endDateTime
	 */
	public String getEndDateTime() {
		return endDateTime;
	}
	/**
	 * @param endDateTime the endDateTime to set
	 */
	public void setEndDateTime(String endDateTime) {
		this.endDateTime = endDateTime;
	}
	
}
