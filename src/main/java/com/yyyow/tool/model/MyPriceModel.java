package com.yyyow.tool.model;

import java.io.Serializable;
import java.util.Objects;

public class MyPriceModel implements Serializable {

    private String symbol;

    private Double minPrice;

    private Double maxPrice;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyPriceModel that = (MyPriceModel) o;
        return Objects.equals(symbol, that.symbol) &&
                Objects.equals(minPrice, that.minPrice) &&
                Objects.equals(maxPrice, that.maxPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, minPrice, maxPrice);
    }
}
