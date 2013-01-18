package com.thoughtworks;

public class CarefulAttendant extends Attendant {
  
	public CarefulAttendant(ValetService... parkingLots) {
		super(parkingLots);
	}

	protected double tooFull() {
		return 1.0;
	}
}
