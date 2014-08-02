package ru.fls.privateoffice.util.service.richbanner;

import org.apache.log4j.Logger;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.util.dto.ProgramElementDTO;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author YMolodkov
 * @since 14.0
 */
@Component
public class RichbannerFilterCheckerImpl implements RichbannerFilterChecker {
    private final static Logger log = Logger.getLogger(RichbannerFilterCheckerImpl.class);


    @Autowired
    private CommonChecker commonChecker;

    public RichbannerFilterCheckerImpl() {
    }

    public RichbannerFilterCheckerImpl(CommonChecker commonChecker) {
        this.commonChecker = commonChecker;
    }

    @Override
    public boolean firstNameCheck(String mask, String firstName, String givenName) {
        log.info("firstNameCheck");
        return commonChecker.fioStringCheck(mask, firstName) || commonChecker.fioStringCheck(mask, givenName);
    }

    @Override
    public boolean middleNameCheck(String mask, String middleName) {
        log.info("middleNameCheck");
        return commonChecker.fioStringCheck(mask, middleName);
    }

    @Override
    public boolean lastNameCheck(String mask, String lastName, String surname) {
        log.info("lastNameCheck");
        return commonChecker.fioStringCheck(mask, surname) || commonChecker.fioStringCheck(mask, lastName);
    }

    @Override
    public boolean genderCheck(String mask, String gender) {
        log.info("genderCheck");
        if ("ALL".equals(mask)) return true;
        return commonChecker.stringCheck(mask, gender);
    }

    @Override
    public boolean accountNumberCheck(String mask, String accountNumber) {
        log.info("accountNumberCheck");
        return commonChecker.strByFormatCheck(mask, accountNumber);
    }


    @Override
    public boolean levelCheck(String[] mask, String level) {
        log.info("levelCheck");
        return commonChecker.arrStringCheck(mask, level);
    }


    @Override
    public boolean balanceFromCheck(Long mask, BigDecimal balance) {
        log.info("balanceFromCheck");
        if (mask == null) return true;
        if (balance == null) return false;
        return mask <= balance.longValue();
    }

    @Override
    public boolean balanceToCheck(Long mask, BigDecimal balance) {
        log.info("balanceToCheck");
        if (mask == null) return true;
        if (balance == null) return false;
        return mask >= balance.longValue();
    }

    @Override
    public boolean registrationDateFromCheck(Date mask, Date registrationDate) {
        log.info("registrationDateFromCheck");
        return commonChecker.dateFromCheck(mask, registrationDate);
    }

    @Override
    public boolean registrationDateToCheck(Date mask, Date registrationDate) {
        log.info("registrationDateToCheck");
        return commonChecker.dateToCheck(mask, registrationDate);
    }

    @Override
    public boolean lastActiveDateFromCheck(Date mask, Date lastActiveDateFrom) {
        log.info("lastActiveDateFromCheck");
        return commonChecker.dateFromCheck(mask, lastActiveDateFrom);
    }

    @Override
    public boolean lastActiveDateToCheck(Date mask, Date lastActiveDateFrom) {
        log.info("lastActiveDateToCheck");
        return commonChecker.dateToCheck(mask, lastActiveDateFrom);
    }

    @Override
    public boolean accountStatusCheck(String[] mask, String accountStatus) {
        log.info("accountStatusCheck");
        return commonChecker.arrStringCheck(mask, accountStatus);
    }

    @Override
    public boolean mailerCheck(Boolean mask, Boolean mailer) {
        log.info("mailerCheck");
        return commonChecker.booleanCheck(mask, mailer);
    }

    @Override
    public boolean emailCheck(String mask, String mailer) {
        log.info("emailCheck");
        return commonChecker.strByFormatCheck(mask, mailer);
    }

    @Override
    public boolean programsCheck(String[] mask, List<ProgramElementDTO> program) {
        log.info("programsCheck");
        if (mask == null || mask.length == 0) return true;
        if (program == null || program.isEmpty()) return false;
        List<String> maskList = Arrays.asList(mask);
        for (ProgramElementDTO programElementDTO : program) {
            if (maskList.contains(programElementDTO.getProgramType())) return true;
        }
        return false;
    }

    @Override
    public boolean infoSourcesCheck(String mask, String source) {
        log.info("infoSourcesCheck");
        return commonChecker.stringCheck(mask, source);
    }

    @Override
    public boolean birthDayCheck(Integer mask, Date birthday) {
        log.info("birthDayCheck");
        if (mask == null) return true;
        if (birthday == null) return false;
        Calendar instance = Calendar.getInstance();
        instance.setTime(birthday);
        return instance.get(Calendar.DAY_OF_MONTH) == mask;
    }

    @Override
    public boolean birthMonthCheck(Integer mask, Date birthday) {
        log.info("birthMonthCheck");
        if (mask == null) return true;
        if (birthday == null) return false;
        Calendar instance = Calendar.getInstance();
        instance.setTime(birthday);
        return instance.get(Calendar.MONTH) == mask;
    }

    @Override
    public boolean birthYearCheck(Integer mask, Date birthday) {
        log.info("birthYearCheck");
        if (mask == null) return true;
        if (birthday == null) return false;
        Calendar instance = Calendar.getInstance();
        instance.setTime(birthday);
        return instance.get(Calendar.YEAR) == mask;
    }

    @Override
    public boolean todayIsBirthday(Boolean mask, Date birthday) {
        log.info("todayIsBirthday");
        if (mask == null) return true;
        if (birthday == null) return false;

        Calendar curBirthdayCal = Calendar.getInstance();
        curBirthdayCal.setTime(new Date());
        curBirthdayCal.set(Calendar.YEAR, (new LocalDate(birthday)).getYear());
        Date curBirthdayDate = curBirthdayCal.getTime();

        boolean isBirthday = commonChecker.dateFromCheck(curBirthdayDate, birthday) && commonChecker.dateToCheck(curBirthdayDate, birthday);
        if (mask) return isBirthday;
        return !isBirthday;
    }

    @Override
    public boolean birthDateFromCheck(Date mask, Date birthDateFrom) {
        log.info("birthDateFromCheck");
        return commonChecker.dateFromCheck(mask, birthDateFrom);
    }

    @Override
    public boolean birthDateToCheck(Date mask, Date birthDateFrom) {
        log.info("birthDateToCheck");
        return commonChecker.dateToCheck(mask, birthDateFrom);
    }

    @Override
    public boolean additionStatusCheck(String[] mask, String additionStatus) {
        log.info("additionStatusCheck");
        return commonChecker.arrStringCheck(mask, additionStatus);
    }

    @Override
    public boolean transliterationMismatchCheck(Boolean mask, Boolean transliterationMismatch) {
        log.info("transliterationMismatchCheck");
        return commonChecker.booleanCheck(mask, transliterationMismatch);
    }


    @Override
    public boolean swindlerCheck(Boolean mask, Boolean swindler) {
        log.info("swindlerCheck");
        return commonChecker.booleanCheck(mask, swindler);
    }

    @Override
    public boolean regionCheck(String mask, String region) {
        log.info("regionCheck");
        return commonChecker.strByFormatCheck(mask, region);
    }

    @Override
    public boolean localityCheck(String mask, String locality) {
        log.info("localityCheck");
        return commonChecker.strByFormatCheck(mask, locality);
    }

    @Override
    public boolean birthPlaceCheck(String mask, String birthPlace) {
        log.info("birth place check");
        return commonChecker.strByFormatCheck(mask, birthPlace);
    }

    @Override
    public boolean addressCommentCheck(String mask, String locality) {
        log.info("addressCommentCheck");
        return commonChecker.strByFormatCheck(mask, locality);
    }

    @Override
    public boolean partnerIdCheck(String mask, List<String> clientIdFromPartner) {
        log.info("partnerIdCheck");
        return commonChecker.stringListCheck(mask, clientIdFromPartner);
    }

    @Override
    public boolean cobrandingPartnerIdCheck(String[] mask, List<String> cobrandingPartnerIds) {
        log.info("cobrandingPartnerIdCheck");
        if (mask == null || mask.length == 0) return true;
        if (cobrandingPartnerIds == null || cobrandingPartnerIds.size() == 0) return false;
        for (String maskEelement : mask) {
            if (cobrandingPartnerIds.contains(maskEelement)) return true;
        }
        return false;
    }

    @Override
    public boolean registrationChannelCheck(String[] mask, String rgistrationPartnerId) {
        log.info("registrationChannelCheck");
        return commonChecker.arrStringCheck(mask, rgistrationPartnerId);
    }

    @Override
    public boolean activeFromCheck(Date activeFrom) {
        log.info("activeFromCheck");
        Date currentDate = new Date();
        return commonChecker.dateTimeFromCheck(activeFrom, currentDate);
    }

    @Override
    public boolean activeToCheck(Date activeTo) {
        log.info("activeToCheck");
        Date currentDate = new Date();
        return commonChecker.dateTimeToCheck(activeTo, currentDate);
    }

    @Override
    public boolean accountExposuresCheck(Integer mask, Integer exposures, Boolean firstVisit) {
        log.info("accountExposuresCheck");
        if (Boolean.TRUE.equals(firstVisit)) mask = 1;
        if (mask == null) return true;
        if (mask == 0) return false;
        if (exposures == null) return true;
        return mask > exposures;
    }
}
