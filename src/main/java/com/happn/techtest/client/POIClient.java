package com.happn.techtest.client;

import com.happn.techtest.dto.NumberOfPOI;
import com.happn.techtest.model.Zone;

import java.util.List;

public interface POIClient {

    NumberOfPOI getNumberOfPOI(String[] args);

    List<Zone> getNDensestZones(String[] args);

}
