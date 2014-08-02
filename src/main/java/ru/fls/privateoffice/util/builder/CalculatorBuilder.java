package ru.fls.privateoffice.util.builder;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.service.CalculatorServiceWrapper;
import ru.fls.privateoffice.util.dto.CalculatorRequestDTO;
import ru.fls.privateoffice.util.dto.IntervalOfPointsDTO;
import ru.rzd.loyalty.api.app.*;

/**
 * Created with IntelliJ IDEA.
 * User: AShutak
 * Date: 09.08.12
 * Time: 19:25
 */
@Component
public class CalculatorBuilder extends AuthorizedPageBuilder {
    private final static Logger log = Logger.getLogger(CalculatorBuilder.class);

    @Autowired
    private CalculatorServiceWrapper calculatorServiceWrapper;

    @Autowired
    private Mapper mapper;
    private static final String STANDARD_ERROR_MSG = "calculator.standardErrMsg";
    private static final String ROUTE_FOR_THIS_STATIONS_DOESNOT_EXIST_MSG = "calculator.routeDidNotFindMsg";


    public IntervalOfPointsDTO buildAccruingPointsIntervalDTO(CalculatorRequestDTO calculatorRequestDTO) {
        try {
            CalculatorRequestType calculatorRequestType = mapCalculDtoToCalculRequestType(calculatorRequestDTO);
            IntInterval intInterval = calculatorServiceWrapper.calculateAccruingPoints(getSessionId(), calculatorRequestType);
            return mapIntIntervalToIntIntervalDTO(intInterval);
        } catch (CalculatorFault fault) {
            log.error("calculateAccruingPoints service error: code = " + fault.getFaultInfo().getCode() + " description = " + fault.getFaultInfo().getMessage());
//            result = fault.getFaultInfo().getCode();
            return getErrorIntervalOfPointsDTO();
        }
    }

    public IntervalOfPointsDTO buildWritingOffPointsIntervalDTO(CalculatorRequestDTO calculatorRequestDTO) {
        try {
            CalculatorRequestType calculatorRequestType = mapCalculDtoToCalculRequestType(calculatorRequestDTO);
            IntInterval intInterval = calculatorServiceWrapper.calculateWritingOffPoints(getSessionId(), calculatorRequestType);
            return mapIntIntervalToIntIntervalDTO(intInterval);
        } catch (CalculatorFault fault) {
            log.error("calculateWritingOffPoints service error: code = " + fault.getFaultInfo().getCode()
                    + " description = " + fault.getFaultInfo().getMessage());
//            result = fault.getFaultInfo().getCode();
            return getErrorIntervalOfPointsDTO();
        }
    }

    private IntervalOfPointsDTO mapIntIntervalToIntIntervalDTO(IntInterval intInterval) {
        if (intInterval == null) {
            log.error("calculatePoints service error: null response");
            return getErrorIntervalOfPointsDTO();
        }
        if (intInterval.getMin() == 0 && intInterval.getMax() == 0){
            log.info("Route for this stations doesn't exist.");
            IntervalOfPointsDTO unexistsInterval = new IntervalOfPointsDTO();
            unexistsInterval.setErrMsg(ROUTE_FOR_THIS_STATIONS_DOESNOT_EXIST_MSG);
            return unexistsInterval;
        }
        if (intInterval.getMin() == -1 && intInterval.getMax() == -1){
            log.info("Route for this stations doesn't exist.");
            return getErrorIntervalOfPointsDTO();
        }
        if (intInterval.getMin() == -2 && intInterval.getMax() == -2){
            log.info("Incompatible scheme type.");
            return getEmptyIntervalOfPointsDTO();
        }
        IntervalOfPointsDTO rez = new IntervalOfPointsDTO();
        int min = intInterval.getMin();
        int max = intInterval.getMax();
        if (min > max){
            log.error("calculatePoints service error: min:"+min+" > max:"+max);
            min = intInterval.getMax();
            max = intInterval.getMin();
        }
        if (min < -1){
            log.error("calculatePoints service error: min:"+min+" < -1");
            if (max < -1){
                log.error("calculatePoints service error: max:"+max+" < -1");
                return getErrorIntervalOfPointsDTO();
            }
            min = max;
        }
        rez.setMin(min);
        rez.setMax(max);
        return rez;
    }

    private CalculatorRequestType mapCalculDtoToCalculRequestType(CalculatorRequestDTO calculatorRequestDTO) {
        CalculatorRequestType calculatorRequestType = new CalculatorRequestType();
        calculatorRequestType.setFromStation(calculatorRequestDTO.getDepartureStation());
        calculatorRequestType.setToStation(calculatorRequestDTO.getArrivalStation());
        calculatorRequestType.setCarType(calculatorRequestDTO.getCarriageType());
        return calculatorRequestType;
    }

    private IntervalOfPointsDTO getErrorIntervalOfPointsDTO() {
        IntervalOfPointsDTO rez = new IntervalOfPointsDTO(0, 0);
        rez.setErrMsg(STANDARD_ERROR_MSG);
        return rez;
    }

    private IntervalOfPointsDTO getEmptyIntervalOfPointsDTO() {
        return new IntervalOfPointsDTO();
    }
}
