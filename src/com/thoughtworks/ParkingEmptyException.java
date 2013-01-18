package com.thoughtworks;

@SuppressWarnings("serial")
public class ParkingEmptyException extends RuntimeException {
  public ParkingEmptyException(String message) {
    super(message);
  }
}