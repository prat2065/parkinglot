package com.gojek.parkinglot;

import static org.testng.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.gojek.parkinglot.ParkingLot;
import com.gojek.vehicle.Bike;
import com.gojek.vehicle.Bus;
import com.gojek.vehicle.Car;
import com.gojek.vehicle.Vehicle;

@Test
public class ParkingLotTest {

	private ParkingLot parkingLot;

	@BeforeMethod
	public void setUp() {
		System.out.println("inside setup");
		this.parkingLot = ParkingLot.getInstance();
	}

	/**
	 * Test for all type of vehicles with single entrance parking lot or one gate closed
	 */
	public void testAllVehiclesWithSinleGateEntrance() {

		Vehicle vehicle = new Bike();
		long uniqueTokenNo = parkingLot.park(vehicle);
		System.out.println("First transaction");
		System.out.println(uniqueTokenNo);
		int price = parkingLot.unparkAndCalculateCost(uniqueTokenNo);
		System.out.println(price);
		assertEquals(price, 20);
		System.out.println("------------First transaction ended-------------");

		vehicle = new Car();
		uniqueTokenNo = parkingLot.park(vehicle);
		System.out.println("Second transaction");
		System.out.println(uniqueTokenNo);
		price = parkingLot.unparkAndCalculateCost(uniqueTokenNo);
		assertEquals(price, 20);
		System.out.println("------------Second transaction ended-------------");

		vehicle = new Bus();
		uniqueTokenNo = parkingLot.park(vehicle);
		System.out.println("Third transaction");
		System.out.println(uniqueTokenNo);
		price = parkingLot.unparkAndCalculateCost(uniqueTokenNo);
		assertEquals(price, 20);
		System.out.println("------------Third transaction ended-------------");

	}

	/**
	 * Testing for multiple gate entrances
	 * gate 1 corresponds to first entrance and gate 2 corresponds to second entrance
	 */
	/**
	 * 
	 */
	public void testBusWithMultipleGatesEntrance() {

		final AtomicInteger countForFailedOccupancy = new AtomicInteger(0);
		final AtomicInteger countForSuccessOccupancy = new AtomicInteger(0);
		final Set<Long> allUniqueTokens = new HashSet<Long>();

		Thread gate1 = new Thread(new Runnable() {

			public void run() {
				for (int i = 1; i <= 10; i++) {
					Vehicle vehicle = new Bus();
					Long uniqueTokenNo = parkingLot.park(vehicle);
					if (uniqueTokenNo == null) {
						countForFailedOccupancy.incrementAndGet();
					} else {
						countForSuccessOccupancy.incrementAndGet();
						allUniqueTokens.add(uniqueTokenNo);
					}
					System.out.println(i + "th" + "transaction in thread " + Thread.currentThread().getName()
							+ "ticket no " + uniqueTokenNo);
				}
			}
		});

		Thread gate2 = new Thread(new Runnable() {

			public void run() {
				for (int i = 1; i <= 10; i++) {
					Vehicle vehicle = new Bus();
					Long uniqueTokenNo = parkingLot.park(vehicle);
					if (uniqueTokenNo == null) {
						countForFailedOccupancy.incrementAndGet();
					} else {
						countForSuccessOccupancy.incrementAndGet();
						allUniqueTokens.add(uniqueTokenNo);
					}
					System.out.println(i + "th" + "transaction in thread " + Thread.currentThread().getName()
							+ "ticket no " + uniqueTokenNo);
				}

			}
		});

		gate1.start();
		gate2.start();
		try {
			gate1.join();
			gate2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(countForSuccessOccupancy.get(), 10);
		assertEquals(countForFailedOccupancy.get(), 10);
		
		unparkAll(allUniqueTokens);
	}

	/**
	 * Method to unpark all the slots.Kind of teardown for the test cases
	 * @param allUniqueTokens
	 */
	private void unparkAll(Set<Long> allUniqueTokens) {
		
		for(Long tokenId : allUniqueTokens) {
			parkingLot.unparkAndCalculateCost(tokenId);
		}
		
	}

	public void testBikeAndCarsForMultipleGateEntrance() {

		final AtomicInteger countForFailedOccupancy = new AtomicInteger(0);
		final AtomicInteger countForSuccessOccupancy = new AtomicInteger(0);
		final Set<Long> allUniqueTokens = new HashSet<Long>();

		Thread gate1 = new Thread(new Runnable() {

			public void run() {
				for (int i = 1; i <= 30; i++) {
					Vehicle vehicle = new Bike();
					Long uniqueTokenNo = parkingLot.park(vehicle);
					if (uniqueTokenNo == null) {
						countForFailedOccupancy.incrementAndGet();
					} else {
						countForSuccessOccupancy.incrementAndGet();
						allUniqueTokens.add(uniqueTokenNo);
					}
					System.out.println(i + "th" + "transaction in thread " + Thread.currentThread().getName()
							+ "ticket no " + uniqueTokenNo);
				}
			}
		});

		Thread gate2 = new Thread(new Runnable() {

			public void run() {
				for (int i = 1; i <= 20; i++) {

					Vehicle vehicle = new Car();
					Long uniqueTokenNo = parkingLot.park(vehicle);
					if (uniqueTokenNo == null) {
						countForFailedOccupancy.incrementAndGet();
					} else {
						countForSuccessOccupancy.incrementAndGet();
						allUniqueTokens.add(uniqueTokenNo);
					}
					System.out.println(i + "th" + "transaction in thread " + Thread.currentThread().getName()
							+ "ticket no " + uniqueTokenNo);
				}
			}
		});
		
		gate1.start();
		gate2.start();
		try {
			gate1.join();
			gate2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		assertEquals(countForSuccessOccupancy.get(), 45);
		assertEquals(countForFailedOccupancy.get(), 5);
		
		unparkAll(allUniqueTokens);

	}
}
