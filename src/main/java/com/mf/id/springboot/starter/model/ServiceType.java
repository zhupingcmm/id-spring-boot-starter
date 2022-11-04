package com.mf.id.springboot.starter.model;

import lombok.Getter;
import lombok.Setter;

public enum ServiceType {
    REDIS("redis"),
    ZOOKEEPER("zookeeper");

    @Getter
    @Setter
    private String name;

    ServiceType(String name) {
        this.name = name;
    }
}
