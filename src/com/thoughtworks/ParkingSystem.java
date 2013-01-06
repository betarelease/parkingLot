package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public abstract class ParkingSystem {

  List<Listener> listeners = new ArrayList<Listener>();
  
  public abstract double capacityRatio();
  public abstract int capacity();

  public abstract boolean canPark();
  public abstract boolean isEmpty();

  public abstract void basicPark();
  public abstract void basicRemove();
  public abstract int size();


    public void park(){
    if (!canPark()) throw new ParkingLotFullException("Attendant cannot park. All lots must be full.");
    basicPark();
    notifyListeners();
  }

  public void remove(){
    if (isEmpty()) throw new ParkingLotEmptyException("Parking lot is empty");
    basicRemove();
    notifyListeners();
  }

  public void notifyListeners() {
    for (Listener listener : listeners) {
      listener.carParkedNotification(this);
    }
  }

  public void register(Listener listener) {
    listeners.add(listener);
  }

}