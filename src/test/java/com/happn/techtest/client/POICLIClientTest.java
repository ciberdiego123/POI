package com.happn.techtest.client;

import com.happn.techtest.business.calculator.POICalculator;
import com.happn.techtest.dto.NumberOfPOI;
import com.happn.techtest.model.Zone;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class POICLIClientTest {

    @Mock
    private POICalculator poiCalculator;

    @InjectMocks
    private POICLIClient poicliClient;

    @ParameterizedTest
    @MethodSource("provideParametersForCorrectNumberOfPOI")
    public void givenCorrectZone_WhenGetNumberOfPOI_ShouldReturnNumberOfPOI(
            String arg0, String arg1, double minLat, double minLon, long value) {
        String[] args = new String[]{arg0, arg1};
        var expectedNumberOfPOI = new NumberOfPOI(value);
        Mockito.when(poiCalculator.getNumberOfPOI(new Zone(minLat, minLon))).thenReturn(value);
        assertEquals(expectedNumberOfPOI, poicliClient.getNumberOfPOI(args));
    }

    private static Stream<Arguments> provideParametersForCorrectNumberOfPOI() {
        return Stream.of(
                Arguments.of("--nbpoi", "{\"min_lat\": 6.5, \"min_lon\": -7}", 6.5, -7, 2),
                Arguments.of("--nbpoi", "{\"min_lat\": 0, \"min_lon\": 0}", 0, 0, 3),
                Arguments.of("--nbpoi", "{\"min_lat\": 1, \"min_lon\": 1.5}", 1, 1.5, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideParametersForIncorrectNumberOfPOI")
    public void givenIncorrectZone_WhenGetNumberOfPOI_ShouldThrowException(
            String arg0, String arg1, double minLat, double minLon, long value) {
        String[] args = new String[]{arg0, arg1};
        assertThrows(RuntimeException.class, () -> {
            poicliClient.getNumberOfPOI(args);
        });

    }

    private static Stream<Arguments> provideParametersForIncorrectNumberOfPOI() {
        return Stream.of(
                Arguments.of("--nbpoi", "{\"min_lat\" 6.5, \"min_lon\": -7}", 6.5, -7, 2),
                Arguments.of("--nbpoi", "{\"min_lat\": 0, \"min_lon\": 0", 0, 0, 3),
                Arguments.of("--nbpoi", "\"min_lat\": 1, \"min_lon\": 1.5}", 1, 1.5, 0)
        );
    }

    @Test
    public void givenCorrectN_WhenGetNDensestZones_ShouldReturnNDensestZones() {
        String[] args = new String[]{"--densest", "{\"n\": 2}"};
        var expectedNDensestZones = List.of(new Zone(0, 1), new Zone(4, 5));
        Mockito.when(poiCalculator.getNDensestZones(2)).thenReturn(expectedNDensestZones);
        assertEquals(expectedNDensestZones, poicliClient.getNDensestZones(args));
    }

    @Test
    public void givenIncorrectN_WhenGetNDensestZones_ShouldThrowException() {
        String[] args = new String[]{"--densest", "[n: 2}"};
        assertThrows(RuntimeException.class, () -> {
            poicliClient.getNDensestZones(args);
        });
    }

}
