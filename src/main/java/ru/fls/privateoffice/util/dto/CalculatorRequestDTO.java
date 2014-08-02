package ru.fls.privateoffice.util.dto;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: AShutak
 * Date: 09.08.12
 * Time: 14:17
 */
public class CalculatorRequestDTO extends AbstractPageDTO{

    @NotNull
    private String departureStation;
    @NotNull
    private String arrivalStation;
    @NotNull
    private String carriageType;

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public String getCarriageType() {
        return carriageType;
    }

    public void setCarriageType(String carriageType) {
        this.carriageType = carriageType;
    }

    @Override
    public String toString() {
        return "CalculatorRequestDTO{" +
                "fromStation='" + departureStation + '\'' +
                ", toStation='" + arrivalStation + '\'' +
                ", carriageType='" + carriageType + '\'' +
                '}';
    }
}
