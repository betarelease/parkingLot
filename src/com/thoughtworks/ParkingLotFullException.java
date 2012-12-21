package com.thoughtworks;

@SuppressWarnings("serial")
public class ParkingLotFullException extends RuntimeException {

  public ParkingLotFullException(String message) {
    super(message);
  }

}
