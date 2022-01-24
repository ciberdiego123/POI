package com.happn.techtest.business.calculator;

import com.happn.techtest.business.reader.POIReader;
import com.happn.techtest.model.POI;
import com.happn.techtest.model.Zone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class POIFileCalculatorTest {

    @Mock
    private POIReader poiReader;

    @InjectMocks
    private POIFileCalculator poiFileCalculator;

    /**
     * Tests getNumberOfPOI
     */

    @Test
    public void givenZone_WhenGetNumberOfPOI_ShouldReturnTwo() {
        List<POI> allPOI = List.of(
                new POI("id1", 6.6, -6.9),
                new POI("id2", 6.8, -6.9),
                new POI("id3", 8.6, -7.7),
                new POI("id4", 5.1, 2.1),
                new POI("id5", 5.1, 2.4)
        );
        Mockito.when(poiReader.readPOI()).thenReturn(allPOI);
        var actualNbOfPOI = poiFileCalculator.getNumberOfPOI(new Zone(6.5, -7));
        assertEquals(2, actualNbOfPOI);
    }

    @Test
    public void givenZone_WhenGetNumberOfPOI_ShouldReturnThree() {
        List<POI> allPOI = List.of(
                new POI("id1", 6.6, -6.9),
                new POI("id2", 6.8, -6.9),
                new POI("id3", 6.6, -6.7),
                new POI("id4", 5.1, 2.1),
                new POI("id5", 5.1, 2.4)
        );
        Mockito.when(poiReader.readPOI()).thenReturn(allPOI);
        var actualNbOfPOI = poiFileCalculator.getNumberOfPOI(new Zone(6.5, -7));
        assertEquals(3, actualNbOfPOI);
    }

    @Test
    public void givenZoneAndPOIEqualToMin_WhenGetNumberOfPOI_ShouldReturnOne() {
        List<POI> allPOI = List.of(
                new POI("id1", 6.5, -7),
                new POI("id2", 6.4, -7)
        );
        Mockito.when(poiReader.readPOI()).thenReturn(allPOI);
        var actualNbOfPOI = poiFileCalculator.getNumberOfPOI(new Zone(6.5, -7));
        assertEquals(1, actualNbOfPOI);
    }

    @Test
    public void givenZoneAndPOIEqualToMax_WhenGetNumberOfPOI_ShouldReturnZero() {
        List<POI> allPOI = List.of(
                new POI("id1", 7, -6.5),
                new POI("id2", 6.4, -7)
        );
        Mockito.when(poiReader.readPOI()).thenReturn(allPOI);
        var actualNbOfPOI = poiFileCalculator.getNumberOfPOI(new Zone(6.5, -7));
        assertEquals(0, actualNbOfPOI);
    }

    @Test
    public void givenZoneAndEmptyAllPOI_WhenGetNumberOfPOI_ShouldReturnZero() {
        List<POI> allPOI = Collections.emptyList();
        Mockito.when(poiReader.readPOI()).thenReturn(allPOI);
        var actualNbOfPOI = poiFileCalculator.getNumberOfPOI(new Zone(6.5, -7));
        assertEquals(0, actualNbOfPOI);
    }

    /**
     * Tests getNDensestZones
     */

    @Test
    public void givenTwo_WhenGetNDensestZones_ShouldReturnTwoZones() {
        List<POI> allPOI = List.of(
                new POI("id1", -48.6, -37.7),
                new POI("id2", -27.1, 8.4),
                new POI("id3", 6.6, -6.9),
                new POI("id4", -2.3, 38.3),
                new POI("id5", 6.8, -6.9),
                new POI("id6", -2.5, 38.3),
                new POI("id7", 0.1, -0.1),
                new POI("id8", -2.1, 38.1)
        );
        Mockito.when(poiReader.readPOI()).thenReturn(allPOI);
        var actualNDensestZones = poiFileCalculator.getNDensestZones(2);
        var expectedNDensestZones = List.of(
                new Zone(-2.5, 38),
                new Zone(6.5, -7)
        );
        assertEquals(expectedNDensestZones, actualNDensestZones);
    }

    @Test
    public void givenTwo_WhenGetNDensestZones_ShouldReturnTwoZonesOrderedDifferently() {
        List<POI> allPOI = List.of(
                new POI("id1", 6.6, -6.9),
                new POI("id2", -2.3, 38.3),
                new POI("id3", 6.8, -6.9),
                new POI("id4", 6.9, -6.7),
                new POI("id5", 6.7, -6.7),
                new POI("id6", -2.5, 38.3),
                new POI("id8", -2.1, 38.1)
        );
        Mockito.when(poiReader.readPOI()).thenReturn(allPOI);
        var actualNDensestZones = poiFileCalculator.getNDensestZones(2);
        var expectedNDensestZones = List.of(
                new Zone(6.5, -7),
                new Zone(-2.5, 38)
        );
        assertEquals(expectedNDensestZones, actualNDensestZones);
    }

    @Test
    public void givenTwoAndEmptyAllPOI_WhenGetNDensestZones_ShouldReturnNoZones() {
        List<POI> allPOI = Collections.emptyList();
        Mockito.when(poiReader.readPOI()).thenReturn(allPOI);
        var actualNDensestZones = poiFileCalculator.getNDensestZones(2);
        var expectedNDensestZones = Collections.emptyList();
        assertEquals(expectedNDensestZones, actualNDensestZones);
    }

}
