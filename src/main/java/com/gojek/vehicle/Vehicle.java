package com.gojek.vehicle;

/**
 * Class defines the properties of the vehicles to be parked in the parking lot
 * @author prateekmukhija
 *@see Bike
 *@see Car
 *@see Bus
 */
public abstract class Vehicle {

	/**
	 * Unique plate no of the vehicle
	 */
	private String noPlate;

	/**
	 * Color of plate if we want to charge differently on private and commercial vehicles
	 */
	private PlateColour colourOfPlate;

	/**
	 * if vehicles can fit in small slot
	 * @return true
	 * if vehicles can not fit in small slot
	 * @return false
	 */
	public abstract boolean canFitInSmallSlot();
	
	/**
	 * if vehicles can fit in compact slot
	 * @return true
	 * if vehicles can not fit in compact slot
	 * @return false
	 */
	public abstract boolean canFitInCompactSlot();
	
	/**
	 * if vehicles can fit in large slot
	 * @return true
	 * if vehicles can not fit in large slot
	 * @return false
	 */
	public abstract boolean canFitInLargeSlot();
	
	public abstract VehicleSizeAndPricing getVehicleSizeAndPricing();

	public String getNoPlate() {
		return noPlate;
	}

	public void setNoPlate(String noPlate) {
		this.noPlate = noPlate;
	}

	public PlateColour getColourOfPlate() {
		return colourOfPlate;
	}

	public void setColourOfPlate(PlateColour colourOfPlate) {
		this.colourOfPlate = colourOfPlate;
	}

}
