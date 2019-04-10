/**
 * 
 */
package com.spothero.request;

import java.util.List;

/**
 * @author nayanankmahajan
 *
 */
public class RateRequest {
	
	
	String days;
	String times;
	String tz;
	Double price;
	/**
	 * @return the days
	 */
	public String getDays() {
		return days;
	}
	/**
	 * @param days the days to set
	 */
	public void setDays(String days) {
		this.days = days;
	}
	/**
	 * @return the times
	 */
	public String getTimes() {
		return times;
	}
	/**
	 * @param times the times to set
	 */
	public void setTimes(String times) {
		this.times = times;
	}
	/**
	 * @return the tz
	 */
	public String getTz() {
		return tz;
	}
	/**
	 * @param tz the tz to set
	 */
	public void setTz(String tz) {
		this.tz = tz;
	}
	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

}
