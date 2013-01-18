package com.thoughtworks;

public abstract class ValetService {
	abstract void park();
	abstract double howFull();
	abstract void accept(ParkingVisitor v);
  abstract int cars() ;

  void parkingFailed() {
		throw new ParkingFullException("No room at the inn!");
	}
}
