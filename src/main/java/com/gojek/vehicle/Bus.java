package com.gojek.vehicle;

/**
 * Vehicle type of large size
 * @author prateekmukhija
 *
 */
public class Bus extends Vehicle {

	@Override
	public boolean canFitInSmallSlot() {
		return false;
	}

	@Override
	public boolean canFitInCompactSlot() {
		return false;
	}

	@Override
	public boolean canFitInLargeSlot() {
		return true;
	}

	@Override
	public VehicleSizeAndPricing getVehicleSizeAndPricing() {
		return VehicleSizeAndPricing.LARGE;
	}

}
