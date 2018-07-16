package com.gojek.parkinglot;

import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.joda.time.DateTime;

import com.gojek.pricing.CalculatePrice;
import com.gojek.slot.CompactSlot;
import com.gojek.slot.LargeSlot;
import com.gojek.slot.Slot;
import com.gojek.slot.SmallSlot;
import com.gojek.vehicle.Vehicle;

/**
 * @author prateekmukhija
 *
 */
public class ParkingLot implements IParkingLot {

	/**
	 * No of small slots in the parking lot
	 */
	private static final int NUM_OF_SMALL_SLOTS = 20;

	/**
	 * No of compact slots in the parking lot
	 */
	private static final int NUM_OF_COMPACT_SLOTS = 15;

	/**
	 * No of large slots in the parking lot
	 */
	private static final int NUM_OF_LARGE_SLOTS = 10;

	private Stack<Slot> smallSlots;

	private Stack<Slot> compactSlots;

	private Stack<Slot> largerSlots;

	/**
	 * Map containing unique txn id vs Slot
	 */
	private Map<Long, Slot> occupiedSlot;

	/**
	 * Map containing unique txn id vs Vehicle
	 */
	private Map<Long, Vehicle> idVsVehicle;

	private Lock lock = new ReentrantLock();

	/**
	 * Singleton instance of @ParkingLot
	 */
	private static ParkingLot PARKING_LOT_INSTANCE = null;

	private ParkingLot() {
		smallSlots = new Stack<Slot>();
		compactSlots = new Stack<Slot>();
		largerSlots = new Stack<Slot>();
		occupiedSlot = new ConcurrentHashMap<Long, Slot>();
		idVsVehicle = new ConcurrentHashMap<Long, Vehicle>();
		createSlots();
	}

	/**
	 * @return singleton object for ParkingLot
	 */
	public static synchronized ParkingLot getInstance() {
		if (PARKING_LOT_INSTANCE == null) {
			synchronized (ParkingLot.class) {
				if (PARKING_LOT_INSTANCE == null) {
					PARKING_LOT_INSTANCE = new ParkingLot();
				}
			}
		}
		return PARKING_LOT_INSTANCE;
	}

	private void createSlots() {

		for (int i = 1; i <= NUM_OF_SMALL_SLOTS; i++) {
			smallSlots.push(new SmallSlot(i));
		}
		for (int i = 1; i <= NUM_OF_COMPACT_SLOTS; i++) {
			compactSlots.push(new CompactSlot(i));
		}
		for (int i = 1; i <= NUM_OF_LARGE_SLOTS; i++) {
			largerSlots.push(new LargeSlot(i));
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Go_Jek.ParkingLot.IParkingLot#park(com.Go_Jek.Vehicle.Vehicle)
	 */
	public Long park(Vehicle vehicle) {
		Long uniqueTokenNo = null;
		Slot slot = getEmptySlot(vehicle);
		if (slot == null)
			return null;
		System.out.println("Parking with thread" + Thread.currentThread().getName());
		uniqueTokenNo = ParkingLotHelper.getUniqueToken();
		slot.setOccupied(true);
		slot.setStartTime(DateTime.now());
		occupiedSlot.put(uniqueTokenNo, slot);
		idVsVehicle.put(uniqueTokenNo, vehicle);
		return uniqueTokenNo;
	}

	private Slot getEmptySlot(Vehicle vehicle) {
		Slot slot = null;
		lock.lock();
		try {
			if (vehicle.canFitInSmallSlot() && isSmallSlotEmpty()) {
				slot = getFirstEmptySlot(smallSlots);
			} else if (vehicle.canFitInCompactSlot() && isCompactSlotEmpty()) {
				slot = getFirstEmptySlot(compactSlots);
			} else if (isLargeSlotEmpty()) {
				slot = getFirstEmptySlot(largerSlots);
			} else {
				System.out.println("No Slot Empty");
			}
		} finally {
			lock.unlock();
		}
		return slot;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.Go_Jek.ParkingLot.IParkingLot#unparkAndCalculateCost(long)
	 */
	public int unparkAndCalculateCost(long uniqueTokenNo) {
		Slot slot = occupiedSlot.get(uniqueTokenNo);
		unpark(slot, uniqueTokenNo);
		int bill = CalculatePrice.calculatePrice(slot, idVsVehicle.get(uniqueTokenNo));
		return bill;
	}

	private void unpark(Slot slot, long uniqueTokenNo) {
		slot.setOccupied(false);
		slot.setEndTime(DateTime.now());
		occupiedSlot.remove(uniqueTokenNo);
		if (slot instanceof SmallSlot) {
			smallSlots.push(slot);
		} else if (slot instanceof CompactSlot) {
			compactSlots.push(slot);
		} else {
			largerSlots.push(slot);
		}
	}

	/**
	 * @return true is large slot is empty
	 */
	private boolean isLargeSlotEmpty() {
		return !largerSlots.isEmpty();
	}

	/**
	 * @return true is compact slot is empty
	 */
	private boolean isCompactSlotEmpty() {

		return !compactSlots.isEmpty();
	}

	/**
	 * @return true is small slot is empty
	 */
	private boolean isSmallSlotEmpty() {

		return !smallSlots.isEmpty();
	}

	private Slot getFirstEmptySlot(Stack<Slot> slots) {
		return slots.pop();
	}

}
