package com.thoughtworks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CarReportTest {

  @Test
  public void shouldProducePrettyReport() {
    ValetService lotOne = new ParkingLot(10);
    ValetService lotTwo = new ParkingLot(4);
    ValetService lotThree = new ParkingLot(5);

    ValetService attendant = new CarefulAttendant(lotTwo, lotThree);
    ValetService manager = new CarefulAttendant(lotOne, attendant);

    lotOne.park();
    lotTwo.park();
    lotThree.park();
    manager.park();

    CarReport r = new CarReport();
    manager.accept(r);
    
    String expectedReport = "Attendant with 4 cars\n" + 
                            "   Attendant with 3 cars\n" +
                            "      Lot with 2 cars\n" +
                            "      Lot with 1 cars\n" +
                            "   Lot with 1 cars\n";
    assertEquals(expectedReport,  r.report());
  }

}
