package com.thoughtworks;

@SuppressWarnings("serial")
public class ParkingFullException extends RuntimeException {
  ParkingFullException(String message){
    super(message);
  }
}
