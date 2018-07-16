package com.gojek.slot;

public class SmallSlot extends Slot {

	public SmallSlot(int slotId) {
		super(slotId);

	}

	@Override
	public SlotType getSlotType() {
		return SlotType.SMALL;
	}

}
