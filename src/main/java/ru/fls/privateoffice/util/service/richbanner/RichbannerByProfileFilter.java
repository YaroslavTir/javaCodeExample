package ru.fls.privateoffice.util.service.richbanner;

import ru.fls.privateoffice.util.dto.AccountBalancePageDTO;
import ru.fls.privateoffice.util.dto.ProfileElementDTO;
import ru.fls.privateoffice.util.dto.ProfilePageDTO;
import ru.fls.privateoffice.util.dto.RichbannerDTO;
import ru.rzd.loyalty.api.app.CobrandingElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author YMolodkov
 * @since 14.0
 */
final public class RichbannerByProfileFilter implements RichbannerFilter {

    final private ProfilePageDTO page;
    final private AccountBalancePageDTO balance;
    final private Set<Long> richbanners;
    final private Map<Long, Integer> exposures;
    final private Set<String> existsRichbannerAccount;


    final private RichbannerFilterChecker richbannerFilterChecker;

    public RichbannerByProfileFilter(ProfilePageDTO page, AccountBalancePageDTO balance, Set<Long> richbanners, Map<Long, Integer> exposures, RichbannerFilterChecker richbannerFilterChecker, Set<String> existsRichbannerAccount) {
        this.page = page;
        this.balance = balance;
        this.richbanners = richbanners;
        this.richbannerFilterChecker = richbannerFilterChecker;
        this.exposures = exposures;
        this.existsRichbannerAccount = existsRichbannerAccount;
    }

    private void fillCobrPartnerId(List<CobrandingElement> cobradings, List<String> clientIdFromPartners, List<String> cobrPartnerId) {
        for (CobrandingElement cobrading : cobradings) {
            clientIdFromPartners.add(cobrading.getClientIdFromPartner());
            if (cobrading.getPartner() != null) cobrPartnerId.add(cobrading.getPartner().getCode());
        }
    }

    @Override
    public boolean accept(RichbannerDTO richbannerDTO) {
        Long richbannerId = richbannerDTO.getId();
        RichbannerClientFilter filterData = richbannerDTO.getFilter();

        if (!richbannerDTO.isActive() ||
                !richbannerFilterChecker.activeFromCheck(richbannerDTO.getActiveFrom()) ||
                !richbannerFilterChecker.activeToCheck(richbannerDTO.getActiveTo()) ||
                !richbannerFilterChecker.accountExposuresCheck(richbannerDTO.getExposures(), exposures.get(richbannerId), richbannerDTO.getFirstVisit())) {
            return false;
        }
        if (richbanners.contains(richbannerId)) return true;
        if (existsRichbannerAccount.contains(richbannerDTO.getId()) && filterData.isEmpty()) return false;

        ProfileElementDTO profile = page.getProfile();
        List<CobrandingElement> cobradings = profile.getCobradings();
        List<String> partnerIds = new ArrayList<String>();
        List<String> cobrPartnerId = new ArrayList<String>();
        fillCobrPartnerId(cobradings, partnerIds, cobrPartnerId);
        String registrationChannel = profile.getRegistrationPartner() != null ? String.valueOf(profile.getRegistrationPartner().getCode()) : null;

        return richbannerFilterChecker.accountNumberCheck(filterData.getAccountNumber(), page.getAccountNumber()) &&
                richbannerFilterChecker.accountStatusCheck(filterData.getAccountStatus(), profile.getAccountStatus()) &&
                richbannerFilterChecker.levelCheck(filterData.getLevel(), profile.getCurrentLevel()) &&
                richbannerFilterChecker.emailCheck(filterData.getEmail(), profile.getEmail()) &&
                richbannerFilterChecker.birthDayCheck(filterData.getBirthDay(), profile.getBirthday()) &&
                richbannerFilterChecker.birthMonthCheck(filterData.getBirthMonth(), profile.getBirthday()) &&
                richbannerFilterChecker.birthYearCheck(filterData.getBirthYear(), profile.getBirthday()) &&
                richbannerFilterChecker.todayIsBirthday(filterData.getTodayIsBirthday(), profile.getBirthday()) &&
                richbannerFilterChecker.lastNameCheck(filterData.getLastName(), profile.getLastName(), profile.getSurname()) &&
                richbannerFilterChecker.firstNameCheck(filterData.getFirstName(), profile.getFirstName(), profile.getGivenName()) &&
                richbannerFilterChecker.middleNameCheck(filterData.getMiddleName(), profile.getMiddleName()) &&
                richbannerFilterChecker.transliterationMismatchCheck(filterData.getTransliterationMismatch(), profile.isTransliterationMismatch()) &&
                richbannerFilterChecker.genderCheck(filterData.getSex(), profile.getSex().toString()) &&
                richbannerFilterChecker.regionCheck(filterData.getRegion(), profile.getAddress().getRegion()) &&
                richbannerFilterChecker.localityCheck(filterData.getLocality(), profile.getAddress().getLocality()) &&
                richbannerFilterChecker.birthPlaceCheck(filterData.getBirthplace(), profile.getBirthplace()) &&
                richbannerFilterChecker.addressCommentCheck(filterData.getAddressComment(), profile.getAddressVtb()) &&
                richbannerFilterChecker.infoSourcesCheck(filterData.getInfoSources(), profile.getInfoSource()) &&
                richbannerFilterChecker.programsCheck(filterData.getPrograms(), profile.getPrograms()) &&
                richbannerFilterChecker.registrationChannelCheck(filterData.getRegistrationChannel(), registrationChannel) &&
                richbannerFilterChecker.partnerIdCheck(filterData.getPartnerId(), partnerIds) &&
                richbannerFilterChecker.cobrandingPartnerIdCheck(filterData.getCobrandingPartnerId(), cobrPartnerId) &&
                richbannerFilterChecker.mailerCheck(filterData.getMailer(), profile.isMailer()) &&
                richbannerFilterChecker.balanceFromCheck(filterData.getBalanceFrom(), balance.getBonus()) &&
                richbannerFilterChecker.balanceToCheck(filterData.getBalanceTo(), balance.getBonus()) &&
                richbannerFilterChecker.registrationDateFromCheck(filterData.getRegistrationDateFrom(), page.getCreateDate()) &&
                richbannerFilterChecker.registrationDateToCheck(filterData.getRegistrationDateTo(), page.getCreateDate()) &&
                richbannerFilterChecker.lastActiveDateFromCheck(filterData.getLastActiveDateFrom(), profile.getLastAccountOperationDate()) &&
                richbannerFilterChecker.lastActiveDateToCheck(filterData.getLastActiveDateTo(), profile.getLastAccountOperationDate()) &&
                richbannerFilterChecker.birthDateFromCheck(filterData.getBirthDateFrom(), profile.getBirthday()) &&
                richbannerFilterChecker.birthDateToCheck(filterData.getBirthDateTo(), profile.getBirthday()) &&
                richbannerFilterChecker.swindlerCheck(filterData.getSwindler(), profile.isSwindler()) &&
                richbannerFilterChecker.additionStatusCheck(filterData.getAdditionStatus(), profile.getAdditionStatus());
    }
}
