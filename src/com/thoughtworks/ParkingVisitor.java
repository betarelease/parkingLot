package com.thoughtworks;

public interface ParkingVisitor {
    void preVisit(ParkingLot lot);
    void postVisit(ParkingLot lot);
    void preVisit(Attendant attendant);
    void postVisit(Attendant attendant);
}
