package com.ticket.system.main.enums;

import java.math.BigDecimal;

public enum EventNames {
    Concert(new BigDecimal("2000.00")),
    Workshop(new BigDecimal("1000.00")),
    Movie(new BigDecimal("1500.00")),
    Festival(new BigDecimal("500.00"));

    private final BigDecimal price;

    EventNames(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
