package ru.fls.privateoffice.util.dto;

import java.io.Serializable;

/**
 * User: NFadin
 * Date: 23.01.14
 * Time: 13:04
 */
public class ReprintingAllowedResultDTO implements Serializable {
    private Integer tripsRemaining;
    private Integer daysRemaining;
    private String status;

    public ReprintingAllowedResultDTO() {
    }

    public ReprintingAllowedResultDTO(Integer tripsRemaining, Integer daysRemaining, String status) {
        this.tripsRemaining = tripsRemaining;
        this.daysRemaining = daysRemaining;
        this.status = status;
    }

    public Integer getTripsRemaining() {
        return tripsRemaining;
    }

    public void setTripsRemaining(Integer tripsRemaining) {
        this.tripsRemaining = tripsRemaining;
    }

    public Integer getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(Integer daysRemaining) {
        this.daysRemaining = daysRemaining;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
