package com.thoughtworks;

@SuppressWarnings("serial")
public class ParkingLotEmptyException extends RuntimeException {

  public ParkingLotEmptyException(String message) {
    super(message);
  }

}
