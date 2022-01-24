package com.happn.techtest.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

import java.util.List;

@Data
@EqualsAndHashCode
public class POI {

    private String id;

    @Setter(AccessLevel.NONE)
    private double latitude;

    @Setter(AccessLevel.NONE)
    private double longitude;

    public POI(String id, double latitude, double longitude) {
        if (latitude < -90 || latitude > 90)
            throw new IllegalArgumentException("Latitude should be a value between -90 and 90");
        if (longitude < -180 || longitude > 180)
            throw new IllegalArgumentException("Longitude should be a value between -180 and 180");
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public boolean existsInsideZone(Zone zone) {
        boolean isInLatitude = latitude >= zone.getMinLatitude() && latitude < zone.getMaxLatitude();
        boolean isInLongitude = longitude >= zone.getMinLongitude() && longitude < zone.getMaxLongitude();
        return isInLatitude && isInLongitude;
    }

    public Zone calculateZone() {
        List<Double> latOptions = List.of(Math.floor(this.latitude), Math.floor(this.latitude) + 0.5);
        List<Double> lonOptions = List.of(Math.floor(this.longitude), Math.floor(this.longitude) + 0.5);
        for (double latOption : latOptions) {
            for (double lonOption : lonOptions) {
                Zone zone = new Zone(latOption, lonOption);
                if (existsInsideZone(zone))
                    return zone;
            }
        }
        return null;
    }

}
