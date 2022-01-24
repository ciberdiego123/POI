package com.happn.techtest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputZone {

    @JsonProperty("min_lat")
    private double minLatitude;

    @JsonProperty("min_lon")
    private double minLongitude;

}
