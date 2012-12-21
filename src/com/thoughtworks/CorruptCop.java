package com.thoughtworks;

public class CorruptCop implements Listener {

  public void carParkingNotification(ParkingLot lot, boolean parked) {
    if (lot.capacity() <= 3) lot.remove();
  }

  public void bribesForTowingCarFrom(ParkingLot parkingLot) {
    parkingLot.register(this);
  }

}