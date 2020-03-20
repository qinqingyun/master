package com.meituan.food.po;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PipelinePrAutoPO implements Serializable {
    private Integer id;

    private Integer department_id;

    private String directionName;

    private String repo;

    private Integer isAutoOn;

    private Integer totalCase;

    public BigDecimal getCoverage() {
        return coverage;
    }

    public void setCoverage(BigDecimal coverage) {
        this.coverage = coverage;
    }

    private BigDecimal coverage;

    private LocalDate auto_date;



}