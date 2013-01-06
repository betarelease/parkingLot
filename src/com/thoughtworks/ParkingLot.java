package com.thoughtworks;

public class ParkingLot extends ParkingSystem {

  int capacity;
  int size;

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

  public boolean canPark() {
    return capacity > 0;
  }

  public boolean isEmpty() {
    return capacity == size;
  }

  public void notifyListeners() {
    for (Listener listener : listeners) {
      listener.carParkedNotification(this);
    }
  }

  public void register(Listener listener) {
    listeners.add(listener);
  }

  public double capacityRatio() {
    return capacity / size;
  }

  @Override
  public void basicPark() {
    capacity--;
  }

  @Override
  public void basicRemove() {
    capacity++;
  }

}