package com.thoughtworks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Attendant extends ParkingSystem implements Listener {

  private static final double NO_LIMIT = 0.0;
  private Set<ParkingSystem> parkingSystems = new HashSet<ParkingSystem>();

  private double limit;

  Attendant() {
    this(NO_LIMIT);
  }

  Attendant(double limit) {
    this.limit = limit;
  }

  public void manage(ParkingSystem parkingSystem) {
    parkingSystem.register(this);
    parkingSystems.add(parkingSystem);
  }

  public boolean canPark() {
    List<ParkingSystem> sortedParkingSystems = new ArrayList<ParkingSystem>(parkingSystems);
    Collections.sort(sortedParkingSystems, new LeastFullComparator());
    parkingSystems = new HashSet<ParkingSystem>(sortedParkingSystems);
    return !parkingSystems.isEmpty();
  }

  public void basicPark() {
    for (ParkingSystem parkingSystem : parkingSystems) {
      parkingSystem.park();
      break;
    }
  }

  public void carParkedNotification(ParkingSystem parkingLot) {
    if (parkingLot.capacityRatio() <= limit) {
      parkingSystems.remove(parkingLot);
    }
    if (parkingLot.capacityRatio() > limit) {
      parkingSystems.add(parkingLot);
    }
  }

  public int capacity() {
    int sum = 0;
    for (ParkingSystem parkingSystem : parkingSystems) {
      sum += parkingSystem.capacity();
    }
    return sum;
  }

  public double capacityRatio() {
    if(parkingSystems.isEmpty()) return 0;
      System.out.println("parking systems size is : " + parkingSystems.size());
    return capacity() / size();
  }

  @Override
  public void basicRemove() {
    for (ParkingSystem parkingSystem : parkingSystems) {
      parkingSystem.remove();
      break;
    }
  }

  @Override
  public boolean isEmpty() {
    return capacity() == 0;
  }

  @Override
  public int size() {
    int sum = 0;
    for (ParkingSystem parkingSystem : parkingSystems) {
      sum += parkingSystem.size();
    }
    return sum;

  }
}

class LeastFullComparator implements Comparator<ParkingSystem> {
  public int compare(ParkingSystem parkingSystem, ParkingSystem other) {
    return (int) (parkingSystem.capacityRatio() - other.capacityRatio());
  }
}