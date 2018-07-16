package com.gojek.vehicle;

/**
 * Vehicle size of medium size
 * @author prateekmukhija
 *
 */
public class Car extends Vehicle {

	@Override
	public boolean canFitInSmallSlot() {
		return false;
	}

	@Override
	public boolean canFitInCompactSlot() {
		return true;
	}

	@Override
	public boolean canFitInLargeSlot() {
		return true;
	}

	@Override
	public VehicleSizeAndPricing getVehicleSizeAndPricing() {
		return VehicleSizeAndPricing.MEDIUM;
	}

	
	
}
