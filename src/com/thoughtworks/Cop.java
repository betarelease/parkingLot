package com.thoughtworks;

public class Cop implements ParkingLotObserver {
  
  public Cop(ParkingLot... parkingLots) {
    for (ParkingLot lot : parkingLots) {
      lot.register(this);
    }
  }

  public void listen(ParkingLot parkingLot, int carsParked, int capacity) {
    if (capacity - carsParked < 3) parkingLot.unpark();
  }
}
