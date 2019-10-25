package com.lanit.webapprest.web.request.vo;

import com.fasterxml.jackson.annotation.JsonValue;

public class Id {
    private Long value;

    public Id() {}

    public Id(long value) {
        setValue(value);
    }

    public Id(String value) {
        setValue(value);
    }

    @JsonValue
    public String toJson() {
        return value.toString();
    }

    public Long getValue() {
        return value;
    }

    public Id setValue(long value) {
        this.value = Long.valueOf(value);
        return this;
    }

    public Id setValue(String id) {
        this.value = id.length() > 0 ? Long.valueOf(id) : null;
        return this;
    }
}
