package com.thoughtworks;

public class CrazyAttendant extends Attendant {

	public CrazyAttendant(ValetService... valetServices) {
		super(valetServices);
	}

	protected double tooFull() {
		return 0.8;
	}
}
