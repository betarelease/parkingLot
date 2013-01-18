package com.thoughtworks;

interface ParkingLotObserver {
	void listen(ParkingLot parkingLot, int carsParked, int capacity);
}
