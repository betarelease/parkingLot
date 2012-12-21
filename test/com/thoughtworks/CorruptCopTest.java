package com.thoughtworks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CorruptCopTest {

  @Test
  public void copTowsCarWhenLessThan3FreeSpaces() {
    CorruptCop corruptCop = new CorruptCop();
    ParkingLot parkingLot = new ParkingLot(10);
    for (int i = 0; i < 7; i++) {
      parkingLot.park();
    }
    corruptCop.bribesForTowingCarFrom(parkingLot);
    parkingLot.park();
    assertEquals(4, parkingLot.capacity());
  }
}
