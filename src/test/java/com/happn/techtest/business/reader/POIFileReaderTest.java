package com.happn.techtest.business.reader;

import com.happn.techtest.model.POI;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class POIFileReaderTest {

    @Test
    public void givenPOIFile_WhenReadPOI_ShouldReturnPOIList() throws IOException {
        POIReader poiReader = new POIFileReader();
        List<POI> expectedAllPOI = List.of(
                new POI("id1", -48.6, -37.7),
                new POI("id2", -27.1, 8.4),
                new POI("id3", 6.6, -6.9),
                new POI("id4", -2.3, 38.3),
                new POI("id5", 6.8, -6.9),
                new POI("id6", -2.5, 38.3),
                new POI("id7", 0.1, -0.1),
                new POI("id8", -2.1, 38.1)
        );
        List<POI> actualAllPOI = poiReader.readPOI();
        assertEquals(expectedAllPOI, actualAllPOI);
    }


}
