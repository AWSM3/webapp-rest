package com.lanit.webapprest.dto;

public class Statistics {
    private long personCount;
    private long carCount;
    private long uniqueVendorCount;

    public Statistics(long personCount, long carCount, long uniqueVendorCount) {
        this.personCount = personCount;
        this.carCount = carCount;
        this.uniqueVendorCount = uniqueVendorCount;
    }

    public long getPersonCount() {
        return personCount;
    }

    public long getCarCount() {
        return carCount;
    }

    public long getUniqueVendorCount() {
        return uniqueVendorCount;
    }
}
