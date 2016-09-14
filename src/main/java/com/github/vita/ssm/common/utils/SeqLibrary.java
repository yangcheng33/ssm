package com.github.vita.ssm.common.utils;


import com.github.vita.ssm.web.vo.BasicVo;

public class SeqLibrary extends BasicVo {
    private static final long serialVersionUID = 1L;

    private String name;
    private Long currValue;
    private Short step;
    private Integer minValue;
    private Long maxValue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCurrValue() {
        return currValue;
    }

    public void setCurrValue(Long currValue) {
        this.currValue = currValue;
    }

    public Short getStep() {
        return step;
    }

    public void setStep(Short step) {
        this.step = step;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Long getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Long maxValue) {
        this.maxValue = maxValue;
    }
}

/*List columns as follows:
"name", "curr_value", "step", "is_cycled", "min_value", "max_value"
*/