package com.gojek.factory;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.gojek.factory.VehicleFactory;
import com.gojek.vehicle.Bike;
import com.gojek.vehicle.Bus;
import com.gojek.vehicle.Car;
import com.gojek.vehicle.Vehicle;

@Test
public class VehicleFactoryTest {

	public void testForBike() {

		Vehicle vehicle = VehicleFactory.getBike();
		assertTrue(vehicle instanceof Bike);
	}

	public void testForCar() {

		Vehicle vehicle = VehicleFactory.getCar();
		assertTrue(vehicle instanceof Car);
	}

	public void testForBus() {

		Vehicle vehicle = VehicleFactory.getBus();
		assertTrue(vehicle instanceof Bus);
	}

}
