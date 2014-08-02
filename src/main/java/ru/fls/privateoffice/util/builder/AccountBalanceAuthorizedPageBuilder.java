package ru.fls.privateoffice.util.builder;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.fls.privateoffice.service.AccountBalanceServiceWrapper;
import ru.fls.privateoffice.util.codes.ProcessingStatuses;
import ru.fls.privateoffice.util.dto.AccountBalancePageDTO;
import ru.rzd.loyalty.api.app.AccountBalanceInformationFault;
import ru.rzd.loyalty.api.app.AccountBalanceResponse;

/**
 * User: NKarataeva
 * Date: 05.04.12
 * Time: 9:37
 */
@Component
public class AccountBalanceAuthorizedPageBuilder extends AuthorizedPageBuilder {
    private final static Logger log = Logger.getLogger(AccountBalanceAuthorizedPageBuilder.class);
    @Autowired
    private AccountBalanceServiceWrapper accountBalanceServiceWrapper;

    @Autowired
    private Mapper mapper;

    public AccountBalancePageDTO buildAccountBalancePageDTO() {
        String result = null;
        AccountBalancePageDTO accountBalancePageDTO = new AccountBalancePageDTO();
        AccountBalanceResponse accountBalanceResponse = null;
        try {
            accountBalanceResponse = accountBalanceServiceWrapper.requestAccountBalance(getSessionId());
            if (accountBalanceResponse != null) {
                accountBalancePageDTO = mapper.map(accountBalanceResponse, AccountBalancePageDTO.class);
            }
            accountBalancePageDTO.setResult(ProcessingStatuses.SUCCESS);
        } catch (AccountBalanceInformationFault fault) {
            log.error("Account balance service error: code = " + fault.getFaultInfo().getCode() + " description = " + fault.getFaultInfo().getMessage());
            result = fault.getFaultInfo().getCode();
        }

        try {
            if (result != null) {
                accountBalancePageDTO.setResult(ProcessingStatuses.getConstant(result));
            }
        } catch (Exception e) {
            accountBalancePageDTO.setResult(ProcessingStatuses.UNKNOWN_ERROR);
        }

        return accountBalancePageDTO;
    }
}
