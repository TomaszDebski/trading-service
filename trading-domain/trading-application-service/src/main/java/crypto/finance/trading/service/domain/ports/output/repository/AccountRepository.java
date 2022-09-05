package crypto.finance.trading.service.domain.ports.output.repository;


import crypto.finance.trading.service.domain.entity.Account;

import java.util.Optional;

public interface AccountRepository {

    Account createAccount(Account account);

    void editAccount(Account account);

    Optional<Account> findByAccountId(Long accountId);
}
