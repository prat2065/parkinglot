package com.gojek.pricing;

import org.joda.time.DateTime;
import org.joda.time.Period;

import com.gojek.slot.Slot;
import com.gojek.vehicle.Vehicle;

/**
 * CalculatePrice class calculates the charge customer should pay for the parking
 * service.This includes a base price and a variable price which depends on the
 * vehicle type and duration for which vehicle was parked.
 * 
 * @author prateekmukhija
 *
 */
public class CalculatePrice {

	/**
	 * Fixed price for every transaction
	 */
	private static final int BASEPRICE = 20;

	/**
	 * @param slot
	 * @param vehicle
	 * @return price
	 */
	public static int calculatePrice(Slot slot, Vehicle vehicle) {

		DateTime parkTime = slot.getStartTime();
		DateTime unparkTime = slot.getEndTime();
		int noOfHours = calculateEffectiveNoOfHours(parkTime, unparkTime);
		int price = BASEPRICE + noOfHours * vehicle.getVehicleSizeAndPricing().getPricePerHour();
		return price;
	}

	private static int calculateEffectiveNoOfHours(DateTime parkTime, DateTime unparkTime) {

		Period period = new Period(parkTime, unparkTime);
		int noOfHours = period.getHours();
		int minutes = period.getMinutes();
		if (minutes % 60 == 0) {
			return noOfHours;
		} else {
			return noOfHours + 1;
		}
	}

}
