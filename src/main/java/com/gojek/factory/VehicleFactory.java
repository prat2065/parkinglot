package com.gojek.factory;

import com.gojek.vehicle.Bike;
import com.gojek.vehicle.Bus;
import com.gojek.vehicle.Car;
import com.gojek.vehicle.Vehicle;

public class VehicleFactory {

	public static Vehicle getBike() {
		return new Bike();
	}
	
	public static Vehicle getCar() {
		return new Car();
	}
	
	public static Vehicle getBus() {
		return new Bus();
	}
}
