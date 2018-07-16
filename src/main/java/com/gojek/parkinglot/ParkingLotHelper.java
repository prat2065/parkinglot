package com.gojek.parkinglot;

/**
 * ParkingLotHelper gives auto-incremented unique id for every transaction done
 * starting from 0.
 * 
 * @author prateekmukhija
 *
 */
public class ParkingLotHelper {

	public static Long tokenNo = 0L;

	@SuppressWarnings("unused")
	public synchronized static Long getUniqueToken() {

		tokenNo++;
		return tokenNo;
	}
}