package crypto.finance.trading.service.domain;

import crypto.finance.trading.service.domain.dto.details.BtcPriceResponse;
import crypto.finance.trading.service.domain.dto.create.account.CreateAccountCommand;
import crypto.finance.trading.service.domain.dto.create.account.CreateAccountResponse;
import crypto.finance.trading.service.domain.dto.details.account.DetailAccountQuery;
import crypto.finance.trading.service.domain.dto.details.account.DetailAccountResponse;
import crypto.finance.trading.service.domain.entity.Account;
import crypto.finance.trading.service.domain.handler.account.AccountCreateCommandHandler;
import crypto.finance.trading.service.domain.handler.account.AccountQueryHandler;
import crypto.finance.trading.service.domain.mapper.AccountDataMapper;
import crypto.finance.trading.service.domain.ports.input.service.AccountApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Slf4j
@Validated
@Service
public class AccountApplicationServiceImpl implements AccountApplicationService {

    private final AccountCreateCommandHandler accountCreateCommandHandler;

    private final AccountQueryHandler accountDetailCommandHandler;

    private final AccountDataMapper accountDataMapper;

    public AccountApplicationServiceImpl(AccountCreateCommandHandler accountCreateCommandHandler,
                                         AccountQueryHandler accountDetailCommandHandler,
                                         AccountDataMapper accountDataMapper) {
        this.accountCreateCommandHandler = accountCreateCommandHandler;
        this.accountDetailCommandHandler = accountDetailCommandHandler;
        this.accountDataMapper = accountDataMapper;
    }

    @Override
    public CreateAccountResponse createAccount(CreateAccountCommand createCustomerCommand) {
        Account account = accountCreateCommandHandler.createAccount(createCustomerCommand);
        return accountDataMapper
                .accountToCreateAccountResponse(account,
                        "Account saved successfully!");
    }

    @Override
    public DetailAccountResponse fetchAccountDetails(DetailAccountQuery detailAccountQuery) {
        return accountDetailCommandHandler.fetchAccountDetails(detailAccountQuery);
    }

    @Override
    public void editAccount(Long accountId, BigDecimal amount, BtcPriceResponse btcPriceResponse) {
        accountCreateCommandHandler.editAccount(accountId, amount, btcPriceResponse);
    }
}
