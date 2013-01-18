package com.thoughtworks;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot extends ValetService {
  private final int capacity;
  private int cars;
  // private Cop dirtyCop;
  private List<ParkingLotObserver> observers = new ArrayList<ParkingLotObserver>();

  public ParkingLot(int capacity) {
    this.capacity = capacity;
    cars = 0;
  }

  public void park() {
    if (cars < capacity) {
      cars++;
      notifyObservers();
    } else parkingFailed();
  }

  private void notifyObservers() {
    for (ParkingLotObserver observer : observers) {
      observer.listen(this, cars, capacity);
    }
  }

  public void unpark() {
    if (cars <= 0) throw new ParkingEmptyException("No cars!");
    cars--;
    notifyObservers();
  }

  public double howFull() {
    return ((double) cars / (double) capacity);
  }

  public void register(ParkingLotObserver observer) {
    observers.add(observer);
  }

  public int cars() {
    return cars;
  }

  public void accept(ParkingVisitor v) {
    v.preVisit(this);
    v.postVisit(this);
  }
}
