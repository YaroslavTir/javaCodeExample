package ru.fls.privateoffice.util.dto;

import org.dozer.Mapping;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: AShutak
 * Date: 09.08.12
 * Time: 16:00
 */
public class IntervalOfPointsDTO extends AbstractPageDTO{
    private Integer min;
    private Integer max;
    private String errMsg;

    public IntervalOfPointsDTO() {
    }

    public IntervalOfPointsDTO(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    /**
     *
     * @return property name, which contains errMsg
     */
    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "IntervalOfPointsDTO{" +
                "min=" + min +
                ", max=" + max +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
