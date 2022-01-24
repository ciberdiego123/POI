package com.happn.techtest.business.calculator;

import com.happn.techtest.model.Zone;

import java.util.List;

public interface POICalculator {

    long getNumberOfPOI(Zone zone);

    List<Zone> getNDensestZones(int n);

}
