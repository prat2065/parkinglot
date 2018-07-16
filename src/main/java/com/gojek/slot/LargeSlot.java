package com.gojek.slot;

public class LargeSlot extends Slot{

	public LargeSlot(int slotId) {
		super(slotId);
	}
	
	@Override
	public SlotType getSlotType() {
		return SlotType.LARGE;
	}
}
