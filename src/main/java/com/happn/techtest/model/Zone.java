package com.happn.techtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.happn.techtest.dto.InputZone;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Zone {

    @JsonProperty("min_lat")
    private double minLatitude;

    @JsonProperty("max_lat")
    private double maxLatitude;

    @JsonProperty("min_lon")
    private double minLongitude;

    @JsonProperty("max_lon")
    private double maxLongitude;

    public Zone(double minLatitude, double minLongitude) {
        this(minLatitude, minLatitude + 0.5, minLongitude, minLongitude + 0.5);
    }

    public Zone(InputZone inputZone) {
        this(inputZone.getMinLatitude(), inputZone.getMinLongitude());
    }

}

