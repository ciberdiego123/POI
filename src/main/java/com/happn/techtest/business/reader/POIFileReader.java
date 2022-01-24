package com.happn.techtest.business.reader;

import com.happn.techtest.model.POI;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class POIFileReader extends CSVReader implements POIReader {

    private static final String inputFileName = "POI.txt";

    public POIFileReader() throws IOException {
        super(new FileReader(new ClassPathResource(inputFileName).getFile()));
        //Space as separator of file
        this.parser = new CSVParserBuilder().withSeparator('\t').build();
        //Ignore header
        this.skipLines = 1;
    }

    @Override
    public List<POI> readPOI() {
        try {
            List<String[]> poiLines = this.readAll();
            return poiLines.stream()
                    .map(line ->
                            new POI(line[0], Double.parseDouble(line[1]), Double.parseDouble(line[2])))
                    .collect(Collectors.toList());
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

}
