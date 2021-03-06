package storage;


import cargo.domain.Cargo;
import carrier.domain.Carrier;
import transportation.domain.Transportation;

import java.util.ArrayList;
import java.util.List;

public class Storage {

  private static final int ARRAY_CAPACITY = 10;

  public static Cargo[] cargos = new Cargo[ARRAY_CAPACITY];
  public static int cargoIndex = 0;

  public static Carrier[] carriers = new Carrier[ARRAY_CAPACITY];
  public static int carrierIndex = 0;

  public static Transportation[] transportations = new Transportation[ARRAY_CAPACITY];
  public static int transportationIndex = 0;

  public static List<Cargo> cargosList = new ArrayList<>();
  public static List<Carrier> carriersList = new ArrayList<>();
  public static List<Transportation> transportationsList = new ArrayList<>();


}
