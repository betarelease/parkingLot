package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot implements Comparable<ParkingLot>{

  private int capacity;
  private int size;
  private List<Listener> listeners = new ArrayList<Listener>();

  public ParkingLot(int size) {
    this.size = size;
    this.capacity = size;
  }

  public int capacity() {
    return capacity;
  }

  public int size() {
    return size;
  }

  public void park() {
    if (capacity <= 0) throw new ParkingLotFullException("No more cars");
    capacity--;
    notifyListeners(true);
  }

  public boolean isFull() {
    return capacity == 0;
  }

  public void remove() {
    if (capacity == size) throw new ParkingLotEmptyException("Parking lot is empty");
    capacity++;
    notifyListeners(false);
  }

  public void notifyListeners(boolean parked) {
    for (Listener listener : listeners) {
      listener.carParkingNotification(this, parked);
    }
  }

  public void register(Listener listener) {
    listeners.add(listener);
  }

  public double capacityRatio() {
    return (double) capacity / (double) size;
  }

  @Override
  public int compareTo(ParkingLot other) {
     return Double.compare(this.capacityRatio(), other.capacityRatio());
  }
}