package com.gojek.slot;

public class CompactSlot extends Slot {

	public CompactSlot(int slotId) {
		super(slotId);
	}

	@Override
	public SlotType getSlotType() {
		return SlotType.COMPACT;
	}
}
