package com.thoughtworks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class Attendant extends ValetService {

	protected List<ValetService> valetServices = new ArrayList<ValetService>();

	public Attendant(ValetService... valetServices) {
		this.valetServices = Arrays.asList(valetServices);
	}

	protected abstract double tooFull();

	public void park() {
		Collections.sort(valetServices, new CarNumbersComparator());
		ValetService lot = canPark(valetServices);
		if (lot != null) { lot.park(); }
		else parkingFailed();
	}

	private ValetService canPark(List<ValetService> lots) {
		for (ValetService lot : lots) {
			if (lot.howFull() < tooFull()) {
				return lot;
			}
		}
		return null;
	}

	public double howFull() {
		double sum = 0.0;
		for (ValetService valetService : valetServices) {
			sum += valetService.howFull();
		}
		return (sum / valetServices.size());
	}

  public int cars() {
    int total = 0;
    for (ValetService valetService : valetServices) {
      total += valetService.cars();
    }
    return total;
  }
  
  
  public  void accept(ParkingVisitor report) {
    report.preVisit(this);
    for(ValetService valetService : valetServices){
        valetService.accept(report);
    }
    report.postVisit(this);
}

}
