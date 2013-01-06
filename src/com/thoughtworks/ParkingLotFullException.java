package com.thoughtworks;

@SuppressWarnings("serial")
public class ParkingLotFullException extends RuntimeException {

  ParkingLotFullException(String message){
    super(message);
  }
}
