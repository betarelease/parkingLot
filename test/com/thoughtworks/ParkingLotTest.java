package com.thoughtworks;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParkingLotTest {

  private ParkingLot parkingLotWithCars(int capacity, int noOfCars) {
    ParkingLot parkingLot = new ParkingLot(capacity);
    for (int i = 0; i < noOfCars; i++) {
      parkingLot.park();
    }
    return parkingLot;
  }

  @Test
  public void parkingLotFull() {
    ParkingLot parkingLot = parkingLotWithCars(5, 5);
    assertTrue(parkingLot.isFull());
  }

  @Test(expected = ParkingLotFullException.class)
  public void cannotPark() {
    ParkingLot parkingLot = parkingLotWithCars(5, 5);
    parkingLot.park();
  }

  @Test
  public void canRemove() {
    ParkingLot fullParkingLot = parkingLotWithCars(5, 5);
    fullParkingLot.remove();
  }

  @Test(expected = ParkingLotEmptyException.class)
  public void cannotRemoveFromEmptyLot() {
    ParkingLot fullParkingLot = parkingLotWithCars(5, 0);
    fullParkingLot.remove();
  }

}