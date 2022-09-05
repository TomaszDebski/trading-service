package crypto.finance.trading.service.domain.handler.account;

import crypto.finance.trading.service.domain.dto.details.BtcPriceResponse;
import crypto.finance.trading.service.domain.dto.create.account.CreateAccountCommand;
import crypto.finance.trading.service.domain.entity.Account;
import crypto.finance.trading.service.domain.exception.AccountDomainException;
import crypto.finance.trading.service.domain.mapper.AccountDataMapper;
import crypto.finance.trading.service.domain.ports.output.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Component
public class AccountCreateCommandHandler {

    private final AccountRepository accountRepository;

    private final AccountDataMapper accountDataMapper;

    public AccountCreateCommandHandler(AccountRepository accountRepository,
                                       AccountDataMapper accountDataMapper) {
        this.accountRepository = accountRepository;
        this.accountDataMapper = accountDataMapper;
    }

    @Transactional
    public Account createAccount(CreateAccountCommand createAccountCommand) {
        Account savedAccount = accountRepository.createAccount(
                accountDataMapper.createAccountCommandToAccount(createAccountCommand));
        if (savedAccount == null) {
            log.error("Could not save account with name: {}", createAccountCommand.getName());
            throw new AccountDomainException("Could not save account with name " +
                    createAccountCommand.getName());
        }
        return savedAccount;
    }

    @Transactional
    public Account editAccount(Long accountId, BigDecimal amount, BtcPriceResponse btcPriceResponse) {
        Optional<Account> findedAccount = accountRepository.findByAccountId(accountId);
        if (findedAccount.isEmpty()) {
            log.error("Could not find account with id: {}", accountId);
            throw new AccountDomainException("Could not find account with id " +
                    accountId);
        }
        Account account = findedAccount.get();
        account.calculateBTC(amount,btcPriceResponse.getPrice());
        accountRepository.editAccount(account);
        return account;
    }
}
