package com.gojek.slot;

import org.joda.time.DateTime;

import com.gojek.slot.Slot;
import com.gojek.slot.SlotType;

public class MockSlot extends Slot {

	public MockSlot(int slotId) {
		super(slotId);
	}

	public void setAllParams(DateTime parkTime, DateTime unparkTime) {

		super.setStartTime(parkTime);
		super.setEndTime(unparkTime);
	}

	@Override
	public SlotType getSlotType() {
		// TODO Auto-generated method stub
		return null;
	}

}
