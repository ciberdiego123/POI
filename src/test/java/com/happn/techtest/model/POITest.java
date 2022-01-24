package com.happn.techtest.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class POITest {

    @ParameterizedTest
    @MethodSource("provideParametersForExistsInsideZoneTrue")
    public void givenCorrectZone_WhenExistsInsideZone_ShouldReturnTrue(
            String id, double latitude, double longitude, double zoneLatitude, double zoneLongitude
    ) {
        POI poi = new POI(id, latitude, longitude);
        assertTrue(poi.existsInsideZone(new Zone(zoneLatitude, zoneLongitude)));
    }

    private static Stream<Arguments> provideParametersForExistsInsideZoneTrue() {
        return Stream.of(
                Arguments.of("id", 0.1, 5.2, 0, 5),
                Arguments.of("id", -0.1, -5.2, -0.5, -5.5),
                Arguments.of("id", 48.6, -7.7, 48.5, -8),
                Arguments.of("id", -11.1, 10.2, -11.5, 10),
                Arguments.of("id", 0, 0, 0, 0),
                Arguments.of("id", 0.4, 0.4, 0, 0),
                Arguments.of("id", 0.5, 0.5, 0.5, 0.5)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersForExistsInsideZoneFalse")
    public void givenCorrectZone_WhenExistsInsideZone_ShouldReturnFalse(
            String id, double latitude, double longitude, double zoneLatitude, double zoneLongitude
    ) {
        POI poi = new POI(id, latitude, longitude);
        assertFalse(poi.existsInsideZone(new Zone(zoneLatitude, zoneLongitude)));
    }

    private static Stream<Arguments> provideParametersForExistsInsideZoneFalse() {
        return Stream.of(
                Arguments.of("id", 0.1, 5.2, 1, 6),
                Arguments.of("id", -0.1, -5.2, 1, 6),
                Arguments.of("id", 48.6, -7.7, 1, 6),
                Arguments.of("id", -11.1, 10.2, 1, 6),
                Arguments.of("id", 0, 0, 0.5, 0.5),
                Arguments.of("id", 0.4, 0.4, 0.5, 0.5),
                Arguments.of("id", 0.5, 0.5, 0, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersForExistsInsideZoneTrue")
    public void givenPOI_WhenCalculateZone_ShouldReturnZone(
            String id, double latitude, double longitude, double zoneLatitude, double zoneLongitude
    ) {
        POI poi = new POI(id, latitude, longitude);
        Zone expectedZone = new Zone(zoneLatitude, zoneLongitude);
        assertEquals(expectedZone, poi.calculateZone());
    }

}
