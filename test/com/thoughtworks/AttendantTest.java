package com.thoughtworks;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.*;

public abstract class AttendantTest {

	protected ParkingLot parkingLot;

	@Before
	public void createAttendants() {
		parkingLot = mock(ParkingLot.class);
	}

	@Test
	public void shouldReturnHowFull() {
		ValetService parkingLotTwo = mock(ParkingLot.class);
		ValetService parkingLotThree = mock(ParkingLot.class);

		Attendant ted = createAttendant(parkingLotTwo, parkingLotThree);
		Attendant bill = createAttendant(parkingLot, ted);
		
		when(parkingLot.howFull()).thenReturn(0.8);
		when(parkingLotTwo.howFull()).thenReturn(0.7);
		when(parkingLotThree.howFull()).thenReturn(0.5);

		assertEquals(0.7, bill.howFull(), 0.01);
		assertEquals(0.6, ted.howFull(), 0.01);	
	}
	
	@Test
	public void shouldParkInFullestValetService() {
		ValetService parkingLotTwo = mock(ParkingLot.class);
		ValetService parkingLotThree = mock(ParkingLot.class);

		Attendant ted = createAttendant(parkingLotTwo, parkingLotThree);
		Attendant bill = createAttendant(parkingLot, ted);
		
		when(parkingLot.howFull()).thenReturn(0.75);
		when(parkingLotTwo.howFull()).thenReturn(0.7);
		when(parkingLotThree.howFull()).thenReturn(0.5);

		try {
			bill.park();
			verify(parkingLot).park();
		}
		catch(IllegalStateException e) {
		}
	}
	
	protected abstract double tooFull();

	protected abstract Attendant createAttendant(ValetService... lots);
}
