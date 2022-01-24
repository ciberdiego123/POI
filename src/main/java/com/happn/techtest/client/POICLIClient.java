package com.happn.techtest.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.happn.techtest.business.calculator.POICalculator;
import com.happn.techtest.dto.InputNumber;
import com.happn.techtest.dto.InputZone;
import com.happn.techtest.dto.NumberOfPOI;
import com.happn.techtest.model.Zone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.logging.Logger;

@Controller
public class POICLIClient implements POIClient, CommandLineRunner {

    private final static Logger LOGGER = Logger.getLogger(POICLIClient.class.getName());

    private final ObjectMapper objectMapper = new ObjectMapper();

    private POICalculator poiCalculator;

    @Autowired
    public POICLIClient(POICalculator poiCalculator) {
        this.poiCalculator = poiCalculator;
    }

    @Override
    public NumberOfPOI getNumberOfPOI(String[] args) {
        // args -> InputZone -> Zone
        try {
            var inputZone = objectMapper.readValue(args[1], InputZone.class);
            var zone = new Zone(inputZone);
            long value = poiCalculator.getNumberOfPOI(zone);
            return new NumberOfPOI(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Zone> getNDensestZones(String[] args) {
        // args -> InputNumber -> n
        try {
            var inputNumber = objectMapper.readValue(args[1], InputNumber.class);
            return poiCalculator.getNDensestZones(inputNumber.getN());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run(String[] args) throws Exception {
        switch (args[0]) {
            case "--nbpoi":
                NumberOfPOI numberOfPOI = getNumberOfPOI(args);
                LOGGER.info(objectMapper.writeValueAsString(numberOfPOI));
                break;
            case "--densest":
                List<Zone> nDensestZones = getNDensestZones(args);
                LOGGER.info(objectMapper.writeValueAsString(nDensestZones));
                break;
            default:
                throw new IllegalArgumentException("The first argument must be --nbpoi or --densest");
        }
    }
}
