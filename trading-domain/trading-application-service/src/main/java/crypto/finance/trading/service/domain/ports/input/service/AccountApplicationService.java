package crypto.finance.trading.service.domain.ports.input.service;

import crypto.finance.trading.service.domain.dto.details.BtcPriceResponse;
import crypto.finance.trading.service.domain.dto.create.account.CreateAccountCommand;
import crypto.finance.trading.service.domain.dto.create.account.CreateAccountResponse;
import crypto.finance.trading.service.domain.dto.details.account.DetailAccountQuery;
import crypto.finance.trading.service.domain.dto.details.account.DetailAccountResponse;

import javax.validation.Valid;
import java.math.BigDecimal;

public interface AccountApplicationService {

    CreateAccountResponse createAccount(@Valid CreateAccountCommand createAccountCommand);

    DetailAccountResponse fetchAccountDetails(@Valid DetailAccountQuery detailAccountQuery);

    void editAccount(Long accountId, BigDecimal amount, BtcPriceResponse btcPriceResponse);
}
