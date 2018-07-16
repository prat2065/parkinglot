package com.gojek.vehicle;

/**
 * Vehicle size of small size
 * @author prateekmukhija
 *
 */
public class Bike extends Vehicle {

	@Override
	public boolean canFitInSmallSlot() {
		return true;
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
		return VehicleSizeAndPricing.SMALL;
	}

}
