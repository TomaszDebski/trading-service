package crypto.finance.trading.service.domain.mapper;

import crypto.finance.trading.service.domain.dto.create.account.CreateAccountCommand;
import crypto.finance.trading.service.domain.dto.create.account.CreateAccountResponse;
import crypto.finance.trading.service.domain.dto.details.account.DetailAccountResponse;
import crypto.finance.trading.service.domain.entity.Account;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountDataMapper {

    public Account createAccountCommandToAccount(CreateAccountCommand createCustomerCommand) {
        return Account.builder()
                .name(createCustomerCommand.getName())
                .usd_balance(createCustomerCommand.getUsd_balance())
                .btc(BigDecimal.ZERO)
                .build();
    }

    public CreateAccountResponse accountToCreateAccountResponse(Account account, String message) {
        return CreateAccountResponse.builder()
                .accountId(account.getId().getValue())
                .name(account.getName())
                .btc(account.getBtc())
                .usd_balance(account.getUsd_balance())
                .build();
    }

    public DetailAccountResponse accountToDetailAccountResponse(Account account) {
        return DetailAccountResponse.builder()
                .accountId(account.getId().getValue())
                .name(account.getName())
                .btc(account.getBtc())
                .usd_balance(account.getUsd_balance())
                .build();
    }

}
