package ru.fls.privateoffice.util.dto;

import org.dozer.Mapping;

import java.text.ParseException;
import java.util.Date;

/**
 * User: NKarataeva
 * Date: 16.04.12
 * Time: 15:22
 */
public class JourneyDataEnteringElementDTO extends AbstractPageDTO {
    @Mapping(value = "journeyDataEnteringGeneralInfo.id")
    private Long id;
    @Mapping(value = "journeyDataEnteringGeneralInfo.useLatin")
    private Boolean useLatin;
    @Mapping(value = "journeyDataEnteringGeneralInfo.travelDate")
    private Date travelDate;
    @Mapping(value = "journeyDataEnteringGeneralInfo.trainNumber")
    private String trainNumber;
    @Mapping(value = "journeyDataEnteringGeneralInfo.ticketNumber")
    private String ticketNumber;
    @Mapping(value = "journeyDataEnteringGeneralInfo.departureStation")
    private String departureStation;
    @Mapping(value = "journeyDataEnteringGeneralInfo.arrivalStation")
    private String arrivalStation;
    @Mapping(value = "journeyDataEnteringGeneralInfo.docType")
    private String docType;
    @Mapping(value = "journeyDataEnteringGeneralInfo.docNumber")
    private String docNumber;

    private Date operationDate;
    private String resultProcess;
    private String rejectReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getResultProcess() {
        return resultProcess;
    }

    public void setResultProcess(String resultProcess) {
        this.resultProcess = resultProcess;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

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

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
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

    public Boolean getUseLatin() {
        return useLatin;
    }

    public void setUseLatin(Boolean useLatin) {
        this.useLatin = useLatin;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
