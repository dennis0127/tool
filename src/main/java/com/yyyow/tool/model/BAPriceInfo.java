package com.yyyow.tool.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * BA price Info
 */
public class BAPriceInfo implements Serializable {
    private String symbol;

    private Double price;

    private Long time;

    public String getSymbol() {
        if (null == symbol) {
            return "";
        }
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        if (null == price) {
            return 0d;
        }
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getTime() {
        if (null == time) {
            return 0l;
        }
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BAPriceInfo that = (BAPriceInfo) o;
        return Objects.equals(symbol, that.symbol) &&
                Objects.equals(price, that.price) &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbol, price, time);
    }
}
