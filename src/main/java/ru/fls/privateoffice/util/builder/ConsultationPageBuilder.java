package ru.fls.privateoffice.util.builder;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.service.ConsultationServiceWrapper;
import ru.fls.privateoffice.util.codes.ProcessingStatuses;
import ru.fls.privateoffice.util.dto.BaseDTO;
import ru.fls.privateoffice.util.dto.ConsultationHistoryPageDTO;
import ru.rzd.loyalty.api.app.ConsultationFault;
import ru.rzd.loyalty.api.app.GetConsultationMessageResponseType;
import ru.rzd.loyalty.api.app.GetConsultationsResponseType;


/**
 * User: NKarataeva
 * Date: 11.07.12
 * Time: 19:46
 */
@Component
public class ConsultationPageBuilder extends AuthorizedPageBuilder {
    private final static Logger log = Logger.getLogger(ConsultationPageBuilder.class);
    @Autowired
    private ConsultationServiceWrapper consultationServiceWrapper;

    @Autowired
    private Mapper mapper;

    public BaseDTO buildConsultationEnteringPageDTO(Long consultationId, String category, Boolean emailNotification, String message, byte[] image, String imageType) {
        String result = null;
        BaseDTO baseDTO = new BaseDTO();
        try {
            consultationServiceWrapper.addClientConsultationMessage(getSessionId(), consultationId, category, emailNotification, message, image, imageType);
            baseDTO.setResult(ProcessingStatuses.SUCCESS);
            log.info("Consultation message added");
        } catch (ConsultationFault fault) {
            log.error("Consultation service error: code = " + fault.getFaultInfo().getCode() + " description = " + fault.getFaultInfo().getMessage());
            result = fault.getFaultInfo().getCode();
        }

        try {
            if (result != null) {
                baseDTO.setResult(ProcessingStatuses.getConstant(result));
            }
        } catch (Exception e) {
            baseDTO.setResult(ProcessingStatuses.UNKNOWN_ERROR);
        }

        return baseDTO;
    }

    public BaseDTO buildAnonymousConsultationEnteringPageDTO(String category, Boolean emailNotification, String message, byte[] image, String imageType, String clientEmail, String phoneNumber, String clientName) {
        String result = null;
        BaseDTO baseDTO = new BaseDTO();
        try {
            consultationServiceWrapper.addAnonymousConsultationMessage(category, emailNotification, message, image, imageType, clientEmail, phoneNumber, clientName);
            baseDTO.setResult(ProcessingStatuses.SUCCESS);
            log.info("Consultation message added");
        } catch (ConsultationFault fault) {
            log.error("Consultation service error: code = " + fault.getFaultInfo().getCode() + " description = " + fault.getFaultInfo().getMessage());
            result = fault.getFaultInfo().getCode();
        }

        try {
            if (result != null) {
                baseDTO.setResult(ProcessingStatuses.getConstant(result));
            }
        } catch (Exception e) {
            baseDTO.setResult(ProcessingStatuses.UNKNOWN_ERROR);
        }

        return baseDTO;
    }

    public ConsultationHistoryPageDTO buildConsultationHistoryPageDTO() {
        String result = null;
        ConsultationHistoryPageDTO historyPageDTO = new ConsultationHistoryPageDTO();
        GetConsultationsResponseType responseType = null;
        try {
            responseType = consultationServiceWrapper.getConsultations(getSessionId());
            if (responseType != null) {
                historyPageDTO = mapper.map(responseType, ConsultationHistoryPageDTO.class);
            }
            historyPageDTO.setResult(ProcessingStatuses.SUCCESS);
            log.info("Consultation message history received");
        } catch (ConsultationFault fault) {
            log.error("Consultation message history service error: code = " + fault.getFaultInfo().getCode() + " description = " + fault.getFaultInfo().getMessage());
            result = fault.getFaultInfo().getCode();
        }

        try {
            if (result != null) {
                historyPageDTO.setResult(ProcessingStatuses.getConstant(result));
            }
        } catch (Exception e) {
            historyPageDTO.setResult(ProcessingStatuses.UNKNOWN_ERROR);
        }

        return historyPageDTO;
    }

    public GetConsultationMessageResponseType getMessageImage(Long messageId) {
        GetConsultationMessageResponseType data = null;
        try {
            data = consultationServiceWrapper.getConsultationMessageImage(getSessionId(), messageId);
        } catch (ConsultationFault fault) {
            log.error("Consultation message history service error: code = " + fault.getFaultInfo().getCode() + " description = " + fault.getFaultInfo().getMessage());
        }

        return data;
    }
}
