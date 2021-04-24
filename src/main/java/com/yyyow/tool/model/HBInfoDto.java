package com.yyyow.tool.model;

import java.util.Objects;

public class HBInfoDto {


    private Double index_price;

    private long index_ts;

    private String contract_code;

    public Double getIndex_price() {
        return index_price;
    }

    public void setIndex_price(Double index_price) {
        this.index_price = index_price;
    }

    public long getIndex_ts() {
        return index_ts;
    }

    public void setIndex_ts(long index_ts) {
        this.index_ts = index_ts;
    }

    public String getContract_code() {
        return contract_code;
    }

    public void setContract_code(String contract_code) {
        this.contract_code = contract_code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HBInfoDto hbInfoDto = (HBInfoDto) o;
        return index_ts == hbInfoDto.index_ts &&
                Objects.equals(index_price, hbInfoDto.index_price) &&
                Objects.equals(contract_code, hbInfoDto.contract_code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index_price, index_ts, contract_code);
    }
}
