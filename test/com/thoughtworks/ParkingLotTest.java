package com.thoughtworks;

import static org.junit.Assert.*;

import org.junit.Test;

import com.thoughtworks.ParkingLot;
import com.thoughtworks.ParkingLotEmptyException;
import com.thoughtworks.ParkingLotFullException;

public class ParkingLotTest {

  private static final double LIMIT_80_PERCENT = 0.2;

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

  @Test(expected = ParkingLotFullException.class)
  public void attendantCannotParkIfLotsFull() {
    Attendant attendant = createAttendantWithFullParkingLots();
    attendant.park();
  }

  private Attendant createAttendantWithFullParkingLots() {
    Attendant attendant = new Attendant();
    ParkingLot fullParkingLot1 = parkingLotWithCars(5, 5);
    ParkingLot fullParkingLot2 = parkingLotWithCars(10, 10);
    attendant.manage(fullParkingLot1);
    attendant.manage(fullParkingLot2);
    return attendant;
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

  @Test
  public void attendantShouldNotBeAbleToParkIfLotIs80Percent() {
    Attendant attendant = createBadAttendantWithParkingLimit80Percent();
    assertAttendantCannotPark(attendant);
  }

  private Attendant createBadAttendantWithParkingLimit80Percent() {
    Attendant badAttendant = new Attendant(LIMIT_80_PERCENT);
    ParkingLot fullParkingLot = parkingLotWithCars(5, 5);
    badAttendant.manage(fullParkingLot);
    fullParkingLot.remove();
    fullParkingLot.remove();
    fullParkingLot.park();
    return badAttendant;
  }

  private void assertAttendantCannotPark(Attendant badAttendant) {
    try {
      badAttendant.park();
      fail("12 year old Attendant should not be able to park");
    } catch (ParkingLotFullException e) {
    }
  }
}