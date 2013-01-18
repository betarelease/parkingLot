package com.thoughtworks;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

public class CopTest {

	protected ParkingLot parkingLot;
	private ParkingLot threeSpaces;
	private ParkingLot fiveSpaces;
	private ParkingLot oneSpace;

	@Before
	public void before() {
		threeSpaces = mock(ParkingLot.class);
		fiveSpaces = mock(ParkingLot.class);
		oneSpace = mock(ParkingLot.class);
	}

		@Test
	 public void shouldRegisterHimselfToParkingLots(){
	
	 Cop percy = new Cop(threeSpaces, fiveSpaces, oneSpace);
	 verify(threeSpaces).register(percy);
	 verify(fiveSpaces).register(percy);
	 verify(oneSpace).register(percy);
	
	 }

	@Test
	public void shouldRegisterWithParkingLot() {
		Cop cop = new Cop(threeSpaces);
		verify(threeSpaces).register(cop);
	}

	@Test
	public void shouldTowCarWhenParkingLotIsTooFull() {
		Cop percy = new Cop(threeSpaces, fiveSpaces, oneSpace);
		percy.listen(threeSpaces, 1, 3);
		verify(threeSpaces).unpark();
	}
}
