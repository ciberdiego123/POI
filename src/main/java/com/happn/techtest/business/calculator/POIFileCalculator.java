package com.happn.techtest.business.calculator;

import com.happn.techtest.business.reader.POIReader;
import com.happn.techtest.model.POI;
import com.happn.techtest.model.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class POIFileCalculator implements POICalculator {

    private POIReader poiReader;

    @Autowired
    public POIFileCalculator(POIReader poiReader) {
        this.poiReader = poiReader;
    }

    @Override
    public long getNumberOfPOI(Zone zone) {
        List<POI> allPOI = poiReader.readPOI();
        return allPOI.stream()
                .filter(poi -> poi.existsInsideZone(zone))
                .count();
    }

    @Override
    public List<Zone> getNDensestZones(int n) {
        Map<Zone, List<POI>> poiByZone = getPOIByZone();
        return poiByZone.entrySet().stream()
                .sorted(Comparator.comparing(entry -> -entry.getValue().size())) // Sorted by not ascending order
                .limit(n)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    private Map<Zone, List<POI>> getPOIByZone() {
        List<POI> allPOI = poiReader.readPOI();
        return allPOI.stream()
                .collect(Collectors.groupingBy(POI::calculateZone));
    }
}
