package com.thoughtworks;

@SuppressWarnings("serial")
public class ParkingLotEmptyException extends RuntimeException {

  ParkingLotEmptyException(String message){
    super(message);
  }
}
