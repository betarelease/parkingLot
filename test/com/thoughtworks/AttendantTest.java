package com.thoughtworks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class AttendantTest {

  private static final double LIMIT_80_PERCENT = 0.2;

  @Test
  public void attendantCannotParkIfLotsFull() {
    Attendant attendant = new Attendant();
    ParkingSystem fullParkingLot1 = parkingLotWithCars(5, 5);
    ParkingSystem fullParkingLot2 = parkingLotWithCars(10, 10);
    attendant.manage(fullParkingLot1);
    attendant.manage(fullParkingLot2);
    try {
      attendant.park();
      fail("Should not be able to park this car");
    } catch (ParkingLotFullException e) {
    }
  }

  private ParkingSystem parkingLotWithCars(int size, int noOfCars) {
    ParkingSystem parkingLot = new ParkingLot(size);
    for (int i = 0; i < noOfCars; i++) {
      parkingLot.park();
    }
    return parkingLot;
  }

  @Test
  public void attendantShouldNotBeAbleToParkIfLotIs80Percent() {
    ParkingSystem attendant = createBadAttendantWithParkingLimit80Percent();
    assertAttendantCannotPark(attendant);
  }

  private ParkingSystem createBadAttendantWithParkingLimit80Percent() {
    Attendant badAttendant = new Attendant(LIMIT_80_PERCENT);
    ParkingSystem fullParkingLot = parkingLotWithCars(5, 5);
    badAttendant.manage(fullParkingLot);
    fullParkingLot.remove();
    fullParkingLot.remove();
    fullParkingLot.park();
    return badAttendant;
  }

  private void assertAttendantCannotPark(ParkingSystem badAttendant) {
    try {
      badAttendant.park();
      fail("12 year old Attendant should not be able to park");
    } catch (ParkingLotFullException e) {
    }
  }

  @Test
  public void attendantShouldBeAbleToParkInLeastFullLot() {
    Attendant attendant = new Attendant();
    ParkingSystem parkingLot1 = parkingLotWithCars(5, 3);
    ParkingSystem parkingLot2 = parkingLotWithCars(10, 7);
    attendant.manage(parkingLot1);
    attendant.manage(parkingLot2);
    attendant.park();
    attendant.park();

    assertEquals(1, parkingLot1.capacity());
    assertEquals(2, parkingLot2.capacity());
  }

  @Test
  public void attendantManagesOtherAttendants() {
    Attendant subAttendant = new Attendant();
    ParkingSystem parkingLot1 = parkingLotWithCars(10, 7);
    subAttendant.manage(parkingLot1);

    Attendant attendant = new Attendant();
    ParkingSystem parkingLot2 = parkingLotWithCars(5, 3);
    attendant.manage(parkingLot2);

    attendant.manage(subAttendant);

    attendant.park();
    attendant.park();

    assertAttendantCannotPark(attendant);
  }

}