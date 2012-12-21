package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {

  private int capacity;
  private int size;

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
  }

  public boolean isFull() {
    return capacity == 0;
  }

  public void remove() {
    if (capacity == size) throw new ParkingLotEmptyException("Parking lot is empty");
    capacity++;
  }

}