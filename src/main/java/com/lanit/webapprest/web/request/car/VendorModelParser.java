package com.lanit.webapprest.web.request.car;

import org.springframework.stereotype.Service;

@Service
public class VendorModelParser {
    private String carName;
    private String vendor;
    private String model;

    public VendorModelParser parse(String carName) {
        this.model = carName;
        int delimiterPosition = carName.indexOf('-');
        if (delimiterPosition <= 0) {
            throw new IllegalArgumentException();
        }
        this.vendor = carName.substring(0, delimiterPosition);

        return this;
    }

    public String getVendor() {
        return vendor;
    }

    public String getModel() {
        return model;
    }
}
