package com.gojek.slot;

import org.joda.time.DateTime;


/**
 * Defines a slot in the parking lot
 * 
 * @author prateekmukhija
 *@see SmallSlot
 *@see CompactSlot
 *@see LargeSlot
 */
public abstract class Slot {

	/**
	 * Unique id for a type for slot.
	 */
	private int slotId;

	/**
	 * Tells if the slot is occupied or not
	 */
	private boolean isOccupied;

	/**
	 * Gives the time when vehicle was parked in the slot
	 */
	private DateTime startTime;

	/**
	 * Gives the time when vehicle is unparked from the slot
	 */
	private DateTime endTime;

	public Slot(int slotId) {

		isOccupied = false;
		this.slotId = slotId;
	}

	public void park() {
		isOccupied = true;
	}

	public void unPark() {
		this.isOccupied = false;
	}

	public boolean equals(Object o) {
		return ((Slot) o).slotId == this.slotId;
	}

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
	}

	public DateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}

	public DateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(DateTime endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * this method returns the type of slot if it is small,medium or large
	 * @return SlotType 
	 */
	public abstract SlotType getSlotType();

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 53 * hash + this.slotId;
		return hash;
	}

}
