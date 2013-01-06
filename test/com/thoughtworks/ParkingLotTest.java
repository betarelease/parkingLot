package com.thoughtworks;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParkingLotTest {

  @Test
  public void parkingLotFull() {
    ParkingSystem parkingLot = parkingLotWithCars(5, 5);
    assertFalse(parkingLot.canPark());
  }

  private ParkingSystem parkingLotWithCars(int capacity, int noOfCars) {
    ParkingSystem parkingLot = new ParkingLot(capacity);
    for (int i = 0; i < noOfCars; i++) {
      parkingLot.park();
    }
    return parkingLot;
  }

  @Test
  public void cannotPark() {
    ParkingSystem parkingLot = parkingLotWithCars(5, 5);

    try {
      parkingLot.park();
      fail("Should throw a RuntimeException when parking lot is full");
    } catch (ParkingLotFullException e) {
    }
  }

  @Test
  public void canRemove() {
    ParkingSystem fullParkingLot = parkingLotWithCars(5, 5);
    fullParkingLot.remove();
  }

  @Test
  public void cannotRemoveFromEmptyLot() {
    ParkingSystem fullParkingLot = parkingLotWithCars(5, 0);
    try {
      fullParkingLot.remove();
      fail("Should not be able to remove this car");
    } catch (RuntimeException e) {
    }
  }
}