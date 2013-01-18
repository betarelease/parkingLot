package com.thoughtworks;

public class CrazyAttendantTest extends AttendantTest {

	protected Attendant createAttendant(ValetService... valets) {
		return new CrazyAttendant(valets);
	}

	protected double tooFull() {
		return 0.8;
	}
}
