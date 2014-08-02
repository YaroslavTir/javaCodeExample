package ru.fls.privateoffice.util.dto;

import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;

/**
 * User: NKarataeva
 * Date: 21.06.12
 * Time: 15:45
 */
public class TicketRequestDTO extends AbstractPageDTO {
    private Date travelDate;
    @NotNull
    private String trainNumber;
    @NotNull
    private String departureStation;
    @NotNull
    private String arrivalStation;
    @NotNull
    private String carriageType;
    @NotNull
    private String place;

    public String getTravelDateString(){
        if (travelDate == null) {
            return null;
        }
        return getFormatter().format(travelDate);
    }

    public void setTravelDateString(String dateString) {
        try {
            travelDate = getFormatter().parse(dateString);
        } catch (ParseException e) {
            travelDate = null;        }

    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
