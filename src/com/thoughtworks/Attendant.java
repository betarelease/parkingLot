package com.thoughtworks;

import java.util.*;

public class Attendant implements Listener {

  private static final double NO_LIMIT = 0.0;
  private List<ParkingLot> availableParkingLots = new ArrayList<ParkingLot>();
  private double limit;

  public Attendant(){
    this(NO_LIMIT);
  }
  
  public Attendant(double limit){
    this.limit = limit;
  }
  public void manage(ParkingLot parkingLot) {
    parkingLot.register(this);
    availableParkingLots.add(parkingLot);
    Collections.sort(availableParkingLots);
  }

  public void park() {
    if (availableParkingLots.isEmpty()) throw new ParkingLotFullException("Attendant cannot park. All lots must be full."); 
    for(ParkingLot parkingLot : availableParkingLots){
      parkingLot.park();
      return;
    }
  }

  public void carParkingNotification(ParkingLot parkingLot, boolean parked) {
    if (parked && parkingLot.capacityRatio() <= limit) {
      availableParkingLots.remove(parkingLot);
    }
    if (!parked && parkingLot.capacityRatio() > limit) {
      availableParkingLots.add(parkingLot);
    }
  }

}
