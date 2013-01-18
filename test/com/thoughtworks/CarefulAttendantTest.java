package com.thoughtworks;

import org.junit.Test;

public class CarefulAttendantTest extends AttendantTest {

	protected CarefulAttendant createAttendant(ValetService... valets) {
		return new CarefulAttendant(valets);
	}

	protected double tooFull() {
		return 1.0;
	}
	
	@Test
	public void shouldDetermineTotalCapacity(){
	  
	}
}
