package ru.fls.privateoffice.util.service.richbanner;

import ru.fls.privateoffice.util.dto.ProgramElementDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * The class implements all checks for a richbanner.
 *
 * @author YMolodkov
 * @since 14.0
 */
public interface RichbannerFilterChecker {

    boolean accountNumberCheck(String mask, String accountNumber);

    boolean firstNameCheck(String mask, String firstName, String givenName);

    boolean middleNameCheck(String mask, String middleName);

    boolean lastNameCheck(String mask, String lastName, String surname);

    boolean genderCheck(String mask, String gender);

    boolean levelCheck(String[] mask, String level);

    boolean balanceFromCheck(Long mask, BigDecimal balance);

    boolean balanceToCheck(Long mask, BigDecimal balance);

    boolean registrationDateFromCheck(Date mask, Date registrationDate);

    boolean registrationDateToCheck(Date mask, Date registrationDate);

    boolean lastActiveDateFromCheck(Date mask, Date lastActiveDateFrom);

    boolean lastActiveDateToCheck(Date mask, Date lastActiveDateFrom);

    boolean accountStatusCheck(String[] mask, String accountStatus);

    boolean mailerCheck(Boolean mask, Boolean mailer);

    boolean emailCheck(String mask, String mailer);

    boolean programsCheck(String[] mask, List<ProgramElementDTO> program);

    boolean infoSourcesCheck(String mask, String source);

    boolean birthDayCheck(Integer mask, Date birthday);

    boolean birthMonthCheck(Integer mask, Date birthday);

    boolean birthYearCheck(Integer mask, Date birthday);

    boolean todayIsBirthday(Boolean mask, Date birthday);

    boolean birthDateFromCheck(Date mask, Date birthDateFrom);

    boolean birthDateToCheck(Date mask, Date birthDateFrom);

    boolean additionStatusCheck(String[] mask, String additionStatus);

    boolean transliterationMismatchCheck(Boolean mask, Boolean transliterationMismatch);

    boolean swindlerCheck(Boolean mask, Boolean swindler);

    boolean regionCheck(String mask, String region);

    boolean localityCheck(String mask, String locality);

    boolean birthPlaceCheck(String mask, String birthPlace);

    boolean addressCommentCheck(String mask, String locality);

    boolean partnerIdCheck(String mask, List<String> clientIdFromPartner);

    boolean cobrandingPartnerIdCheck(String[] mask, List<String> cobrandingPartnerIds);

    boolean registrationChannelCheck(String[] mask, String rgistrationPartnerId);

    boolean activeFromCheck(Date activeFrom);

    boolean activeToCheck(Date activeTo);

    boolean accountExposuresCheck(Integer mask, Integer exposures, Boolean firstVisit);

}
