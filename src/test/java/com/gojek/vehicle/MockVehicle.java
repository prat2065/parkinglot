package com.gojek.vehicle;

import com.gojek.vehicle.PlateColour;
import com.gojek.vehicle.Vehicle;
import com.gojek.vehicle.VehicleSizeAndPricing;

public class MockVehicle extends Vehicle {

	private VehicleSizeAndPricing vehicleSizingAndPricing;
	
	private boolean canFitInSmallSlot;
	
	private boolean canFitInCompactSlot;
	
	private boolean canFitInLargeSlot;

	@Override
	public boolean canFitInSmallSlot() {
		return canFitInSmallSlot;
	}

	@Override
	public boolean canFitInCompactSlot() {
		return canFitInCompactSlot;
	}

	@Override
	public boolean canFitInLargeSlot() {
		return canFitInLargeSlot;
	}

	@Override
	public VehicleSizeAndPricing getVehicleSizeAndPricing() {
		return vehicleSizingAndPricing;
	}
	
	public void setCanFitInSmallSlot(boolean canFitInSmallSlot) {
		this.canFitInSmallSlot = canFitInSmallSlot;
	}

	public void setCanFitInCompactSlot(boolean canFitInCompactSlot) {
		this.canFitInCompactSlot = canFitInCompactSlot;
	}

	public void setCanFitInLargeSlot(boolean canFitInLargeSlot) {
		this.canFitInLargeSlot = canFitInLargeSlot;
	}


	public void setOtherParams(String noPlate, PlateColour colorPlate,VehicleSizeAndPricing vehicleSizeAndPricing) {

		super.setColourOfPlate(colorPlate);
		super.setNoPlate(noPlate);
	    this.vehicleSizingAndPricing = vehicleSizeAndPricing;
	}

}
