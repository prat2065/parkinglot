package com.gojek.parkinglot;

import com.gojek.vehicle.Vehicle;

/**
 * 
 * IParkingLot provides the interface for parking and unparking the vehicles in
 * the available slots.Three types of vehicles are allowed to be parked in the
 * slot.A small ones like two-wheelers,compact ones like small trucks and large
 * one buses. If the small slot is available then that slot will be given to the
 * vehicle otherwise the available slots from the compact or the larger ones can
 * also be given.
 * 
 * @author prateekmukhija
 * @see ParkingLot
 */
public interface IParkingLot {

	/**
	 * Used to park the vehicle in the available slot.
	 * 
	 * @param vehicle
	 * @return unique token id for the reference to the customer, @return null if no
	 *         slot is available.
	 */
	public Long park(Vehicle vehicle);

	/**
	 * Used to unpark the vehicle and to calculate the cost for the service based on
	 * the vehicle and duration for which the vehicle was parked.
	 * 
	 * @param uniqueTokenNo
	 * @return the price customer has to pay.
	 */
	public int unparkAndCalculateCost(long uniqueTokenNo);

}
