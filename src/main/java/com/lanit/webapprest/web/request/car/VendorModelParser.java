package com.lanit.webapprest.web.request.car;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class VendorModelParser {
    private String carName;
    private String vendor;
    private String model;

    public VendorModelParser parse(String carName) {
        this.carName = carName;
        int delimiterPosition = carName.indexOf('-');
        this.vendor = carName.substring(0, delimiterPosition);
        this.model = carName.substring(delimiterPosition + 1, carName.length());

        return this;
    }

    public String getVendor() {
        return vendor;
    }

    public String getModel() {
        return model;
    }
}
