package com.thoughtworks;

public class CarReport implements ParkingVisitor {

  private StringBuffer output = new StringBuffer("");
  private int spaceCount = 0;
  private final int INDENT_COUNT = 3;

  public void preVisit(ParkingLot lot) {
    space();
    output.append("Lot with " + lot.cars() + " cars\n");
  }

  public void postVisit(ParkingLot lot) {
    // Do nothing
  }

  public void preVisit(Attendant attendant) {
    space();
    output.append("Attendant with " + attendant.cars() + " cars\n");
    spaceCount += INDENT_COUNT;
  }

  public void postVisit(Attendant attendant) {
    spaceCount -= INDENT_COUNT;
  }

  public String report() {
    return output.toString();
  }

  private void space() {
    for (int i = 0; i < spaceCount; i++)
      output.append(" ");
  }

}
