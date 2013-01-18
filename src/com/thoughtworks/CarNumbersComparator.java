package com.thoughtworks;

import java.util.Comparator;

public class CarNumbersComparator implements Comparator<ValetService> {
  
  public int compare(ValetService first, ValetService second) {
    return (int) ((second.howFull() - first.howFull()) * 100);
  }
}
