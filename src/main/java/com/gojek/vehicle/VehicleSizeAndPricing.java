package com.gojek.vehicle;

import java.util.HashMap;
import java.util.Map;

/**
 * Enum for pricing the vehicles on the basis of size
 * @author prateekmukhija
 *
 */
public enum VehicleSizeAndPricing {

	SMALL(0,20), MEDIUM(1,50), LARGE(2,100);

	private final int size;
	
	private final int pricePerHour;
	
	private Map<Integer,VehicleSizeAndPricing> sizeVsEnumMap = new HashMap<Integer, VehicleSizeAndPricing>();

	private VehicleSizeAndPricing(int size,int pricePerHour) {
		this.size = size;
		this.pricePerHour = pricePerHour;
		sizeVsEnumMap.put(size, this);
	}
	
	public int getIntegerValue() {
		return this.size;
	}
	
	public int getPricePerHour() {
		return this.pricePerHour;
	}
	
	public int pricePerHour(int size) {
		VehicleSizeAndPricing vehicleSize = sizeVsEnumMap.get(size);
		return vehicleSize.getPricePerHour();
	}
}
