package com.lanit.webapprest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Statistics {
    private long personCount;
    private long carCount;
    private long uniqueVendorCount;

    public Statistics(long personCount, long carCount, long uniqueVendorCount) {
        this.personCount = personCount;
        this.carCount = carCount;
        this.uniqueVendorCount = uniqueVendorCount;
    }

    @JsonProperty("personcount")
    public long getPersonCount() {
        return personCount;
    }

    @JsonProperty("carcount")
    public long getCarCount() {
        return carCount;
    }

    @JsonProperty("uniquevendorcount")
    public long getUniqueVendorCount() {
        return uniqueVendorCount;
    }
}
