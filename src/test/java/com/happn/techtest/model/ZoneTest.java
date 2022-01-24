package com.happn.techtest.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZoneTest {

    @ParameterizedTest
    @MethodSource("provideParametersForZoneConstructor")
    public void givenMinValues_WhenZoneConstructor_ShouldReturnZone(
            double minLatitude, double minLongitude, double maxLatitude, double maxLongitude
    ) {
        Zone actualZone = new Zone(minLatitude, minLongitude);
        Zone expectedZone = new Zone(minLatitude, maxLatitude, minLongitude, maxLongitude);
        assertEquals(expectedZone, actualZone);
    }

    private static Stream<Arguments> provideParametersForZoneConstructor() {
        return Stream.of(
                Arguments.of(0, 5, 0.5, 5.5),
                Arguments.of(-0.5, -5.5, 0, -5),
                Arguments.of(48.5, -8, 49, -7.5),
                Arguments.of(-11.5, 10, -11, 10.5),
                Arguments.of(0, 0, 0.5, 0.5),
                Arguments.of(0.5, 0.5, 1, 1)
        );
    }

}
