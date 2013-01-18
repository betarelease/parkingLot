package com.thoughtworks;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ParkingLotTest {
  private ParkingLot parkingLot;

  @Before
  public void createAParkingLotWithCapacity3() {
    parkingLot = new ParkingLot(3);
  }

  @Test
  public void shouldRevealHowFullLotIs() {
    assertEquals(0, parkingLot.howFull(), 0.01);
    parkingLot.park();
    assertEquals(0.33, parkingLot.howFull(), 0.01);
    parkingLot.park();
    assertEquals(0.67, parkingLot.howFull(), 0.01);
  }

  @Test(expected = ParkingFullException.class)
  public void shouldAllowParkingWhenASpaceIsOpen() {
    parkingLot.park();
    parkingLot.park();
    parkingLot.park();
    parkingLot.park();
  }

  @Test(expected = ParkingEmptyException.class)
  public void shouldAllowUnparkingWhenThereIsACarInTheLot() {
    parkingLot.park();
    parkingLot.unpark();
    parkingLot.unpark();
  }

}
