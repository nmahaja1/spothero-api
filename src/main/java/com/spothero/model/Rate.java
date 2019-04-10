package com.spothero.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GenerationType;

@Entity
public class Rate {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rate_id;
	
	private String day;

    private Integer startTime;
	
    private Integer endTime;
	
	private String timeZone;
	
	private Double price;

	
	public Rate() {
		super();
	}

	/**
	 * @param day
	 * @param startTime
	 * @param endTime
	 * @param timeZone
	 * @param price
	 */
	public Rate(String day, Integer startTime, Integer endTime, String timeZone, Double price) {
		super();
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.timeZone = timeZone;
		this.price = price;
	}

	/**
	 * @return the rate_id
	 */
	public Long getRate_id() {
		return rate_id;
	}

	/**
	 * @param rate_id the rate_id to set
	 */
	public void setRate_id(Long rate_id) {
		this.rate_id = rate_id;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	/**
	 * @return the startTime
	 */
	public Integer getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Integer getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the timeZone
	 */
	public String getTimeZone() {
		return timeZone;
	}

	/**
	 * @param timeZone the timeZone to set
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
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

	@Override
	public String toString() {
		return "Rate [rate_id=" + rate_id + ", day=" + day + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", timeZone=" + timeZone + ", price=" + price + "]";
	}

}
