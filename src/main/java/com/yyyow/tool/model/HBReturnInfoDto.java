package com.yyyow.tool.model;

import java.util.List;
import java.util.Objects;

public class HBReturnInfoDto {
    private String status;

    private List<HBInfoDto> data;

    private Long ts;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<HBInfoDto> getData() {
        return data;
    }

    public void setData(List<HBInfoDto> data) {
        this.data = data;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HBReturnInfoDto that = (HBReturnInfoDto) o;
        return Objects.equals(status, that.status) &&
                Objects.equals(data, that.data) &&
                Objects.equals(ts, that.ts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, data, ts);
    }
}
