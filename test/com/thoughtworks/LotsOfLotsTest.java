package com.thoughtworks;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class LotsOfLotsTest {
  private ParkingLot fiveSpaces;
  private ParkingLot threeSpaces;
  private ParkingLot oneSpace;
  private Attendant crazyBill;
  private CarefulAttendant fred;
  private CarefulAttendant george;

  @Before
  public void createLotsOfLots() {
    threeSpaces = new ParkingLot(3);
    fiveSpaces = new ParkingLot(5);
    oneSpace = new ParkingLot(1);
    crazyBill = new CrazyAttendant(threeSpaces, fiveSpaces, oneSpace);
    fred = new CarefulAttendant(threeSpaces, fiveSpaces, oneSpace);
    george = new CarefulAttendant(threeSpaces, fiveSpaces, oneSpace);
  }

  @Test
  public void attendantsShouldParkInFirstAvailableLotWithSpace() {
    // Given all parking lots empty
    // When
    fred.park();
    fred.park();
    fred.park();
    george.park();
    // Then
    assertEquals(1.0, threeSpaces.howFull(), 0.01);
    assertEquals(0.2, fiveSpaces.howFull(), 0.01);
    assertEquals(0.0, oneSpace.howFull(), 0.01);
  }

  @Test
  public void crazyAttendantsShouldParkInFirstAvailableLotWithSpace() {
    // Given all parking lots empty
    // When
    crazyBill.park();
    crazyBill.park();
    crazyBill.park();
    // Then
    assertEquals(1.0, threeSpaces.howFull(), 0.01);
    // When
    crazyBill.park();
    crazyBill.park();
    crazyBill.park();
    crazyBill.park();
    // Then
    assertEquals(0.8, fiveSpaces.howFull(), 0.01);
    // When
    crazyBill.park();
    assertEquals(0.8, fiveSpaces.howFull(), 0.01);
    assertEquals(1.0, oneSpace.howFull(), 0.01);
  }

  @Test
  public void copShouldRemoveCarWhenAlmostFull() {
    CarefulAttendant charlie = new CarefulAttendant(fiveSpaces);
    @SuppressWarnings("unused")
    Cop percy = new Cop(fiveSpaces);
    charlie.park();
    charlie.park();
    assertEquals(.4, fiveSpaces.howFull(), 0.01);
    charlie.park();
    assertEquals(.4, fiveSpaces.howFull(), 0.01);
  }

  @Test
  public void attendantShouldParkInFullestParkingLot() {
    ParkingLot threeSpaces = new ParkingLot(3);
    ParkingLot fiveSpaces = new ParkingLot(5);
    ParkingLot tenSpaces = new ParkingLot(10);
    Attendant sally = new CarefulAttendant(tenSpaces);
    Attendant bob = new CarefulAttendant(threeSpaces, fiveSpaces, tenSpaces);
    sally.park();
    bob.park();
    assertEquals(0.2, tenSpaces.howFull(), 0.01);
  }

  @Test
  public void attendantShouldFindBestParkingLot() {
    final int CAPACITY = 10;
    ParkingLot parkingLot1 = new ParkingLot(CAPACITY);
    ParkingLot parkingLot2 = new ParkingLot(CAPACITY);
    ParkingLot parkingLot3 = new ParkingLot(CAPACITY);
    ParkingLot parkingLot4 = new ParkingLot(CAPACITY);
    ParkingLot parkingLot5 = new ParkingLot(CAPACITY);
    ParkingLot parkingLot6 = new ParkingLot(CAPACITY);
    Attendant carlos = new CarefulAttendant(parkingLot3);
    Attendant mimi = new CarefulAttendant(parkingLot4, parkingLot5);
    Attendant aloysius = new CarefulAttendant(parkingLot6);
    Attendant boss = new CarefulAttendant(parkingLot1, parkingLot2, mimi, carlos, aloysius);
    for (int i = 0; i < 3; ++i, parkingLot1.park())
      ;
    for (int i = 0; i < 4; ++i, parkingLot2.park())
      ;
    parkingLot3.park();
    for (int i = 0; i < 7; ++i, parkingLot4.park())
      ;
    parkingLot5.park();
    boss.park();
    assertEquals(0.3, parkingLot1.howFull(), 0.01);
    assertEquals(0.5, parkingLot2.howFull(), 0.01);
    assertEquals(0.1, parkingLot3.howFull(), 0.01);
    assertEquals(0.7, parkingLot4.howFull(), 0.01);
    assertEquals(0.1, parkingLot5.howFull(), 0.01);
    assertEquals(0.0, parkingLot6.howFull(), 0.01);
  }

  @Test(expected = ParkingFullException.class)
  public void shouldFailIfTryToExceedOverallCapacity() {
    final int CAPACITY = 10;
    ParkingLot parkingLot1 = new ParkingLot(CAPACITY);
    ParkingLot parkingLot2 = new ParkingLot(CAPACITY);
    Attendant carlos = new CarefulAttendant(parkingLot1);
    Attendant mimi = new CarefulAttendant(parkingLot2);
    Attendant boss = new CarefulAttendant(mimi, carlos);
    for (int i = 0; i < CAPACITY; i++) {
      parkingLot1.park();
      parkingLot2.park();
    }
    boss.park();
  }

  @Test
  public void valetServiceShouldParkCar() {
    final int CAPACITY = 10;
    ValetService lotOne = new ParkingLot(CAPACITY);
    ValetService attendantOne = new CarefulAttendant(lotOne);
    attendantOne.park();
    assertEquals(0.1, ((ParkingLot) lotOne).howFull(), 0.01);
  }

  @Test
  public void valetServiceShouldChooseBestCarLotAndPark() {
    ValetService lotOne = new ParkingLot(10);
    ValetService lotTwo = new ParkingLot(4);
    ValetService lotThree = new ParkingLot(5);
    ValetService attendantTwo = new CarefulAttendant(lotTwo, lotThree);
    ValetService attendantOne = new CarefulAttendant(lotOne, attendantTwo);
    try {
      lotOne.park();
      lotTwo.park();
      lotThree.park();
      attendantOne.park();
    } catch (IllegalStateException e) {
    }
    assertEquals(0.1, lotOne.howFull(), 0.01);
    assertEquals(0.5, lotTwo.howFull(), 0.01);
    assertEquals(0.2, lotThree.howFull(), 0.01);
  }
}
