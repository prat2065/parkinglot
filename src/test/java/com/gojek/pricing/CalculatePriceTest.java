package com.gojek.pricing;

import org.joda.time.DateTime;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gojek.pricing.CalculatePrice;
import com.gojek.slot.MockSlot;
import com.gojek.vehicle.MockVehicle;
import com.gojek.vehicle.PlateColour;
import com.gojek.vehicle.VehicleSizeAndPricing;

/**
 * CalculatePriceTest is used for testing the pricing service for Parking lot
 * @author prateekmukhija
 *
 */
@Test
public class CalculatePriceTest {

	private MockSlot slot1;
	
	private MockSlot slot2;
	
	private MockVehicle vehicle1;
	
	private MockVehicle vehicle2;
	
	@BeforeMethod
	public void setUp() {
		
		vehicle1 = new MockVehicle();
		vehicle2 = new MockVehicle();
		vehicle1.setOtherParams("RJ147890", PlateColour.WHITE ,VehicleSizeAndPricing.SMALL);
		vehicle2.setOtherParams("RJ147000", PlateColour.YELLOW ,VehicleSizeAndPricing.MEDIUM);
		slot1 = new MockSlot(1);
		slot2 = new MockSlot(2);
		DateTime unparkTime1 = new DateTime();
		DateTime parkTime1 = unparkTime1.minusMinutes(100);
		DateTime unparkTime2 = new DateTime();
		DateTime parkTime2 = unparkTime2.minusMinutes(120);
		slot1.setAllParams(parkTime1, unparkTime1);
		slot2.setAllParams(parkTime2, unparkTime2);
	}
	
	public void testPositiveCase() {
		Assert.assertEquals(CalculatePrice.calculatePrice(slot1, vehicle1), 20 + 2 * VehicleSizeAndPricing.SMALL.getPricePerHour());
		Assert.assertEquals(CalculatePrice.calculatePrice(slot2, vehicle2), 20 + 2 * VehicleSizeAndPricing.MEDIUM.getPricePerHour());
	}
}
