package crypto.finance.trading.service.domain.handler.account;

import crypto.finance.trading.service.domain.dto.details.account.DetailAccountQuery;
import crypto.finance.trading.service.domain.dto.details.account.DetailAccountResponse;
import crypto.finance.trading.service.domain.entity.Account;
import crypto.finance.trading.service.domain.exception.AccountDomainException;
import crypto.finance.trading.service.domain.mapper.AccountDataMapper;
import crypto.finance.trading.service.domain.ports.output.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class AccountQueryHandler {

    private final AccountDataMapper accountDataMapper;

    private final AccountRepository accountRepository;

    public AccountQueryHandler(AccountDataMapper accountDataMapper,
                               AccountRepository accountRepository) {
        this.accountDataMapper = accountDataMapper;
        this.accountRepository = accountRepository;
    }

    @Transactional(readOnly = true)
    public DetailAccountResponse fetchAccountDetails(DetailAccountQuery detailAccountQuery) {
           Optional<Account> orderResult =
                   accountRepository.findByAccountId(detailAccountQuery.getAccountId());
           if (orderResult.isEmpty()) {
               log.warn("Could not find account with id: {}", detailAccountQuery.getAccountId());
               throw new AccountDomainException("Could not find account with id: " +
                       detailAccountQuery.getAccountId());
           }
           return accountDataMapper.accountToDetailAccountResponse(orderResult.get());
    }
}
