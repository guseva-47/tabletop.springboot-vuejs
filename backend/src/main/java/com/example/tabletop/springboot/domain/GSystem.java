package com.example.tabletop.springboot.domain;

public enum GSystem {
    HONEYHEIST("Honey heist"), CRASHPANDAS("Crash pandas");
    public String title;
    GSystem(String title) {
        this.title = title;
    }
}
