package crypto.finance.trading.service.dataaccess.account.adapter;

import crypto.finance.trading.service.dataaccess.account.mapper.AccountDataAccessMapper;
import crypto.finance.trading.service.dataaccess.account.repository.AccountJpaRepository;
import crypto.finance.trading.service.domain.entity.Account;
import crypto.finance.trading.service.domain.ports.output.repository.AccountRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountRepositoryImpl implements AccountRepository{

    private final AccountJpaRepository accountJpaRepository;

    private final AccountDataAccessMapper accountDataAccessMapper;

    public AccountRepositoryImpl(AccountJpaRepository accountJpaRepository,
                                 AccountDataAccessMapper accountDataAccessMapper) {
        this.accountJpaRepository = accountJpaRepository;
        this.accountDataAccessMapper = accountDataAccessMapper;
    }

    @Override
    public Account createAccount(Account account) {
        return accountDataAccessMapper.accountEntityToAccount(
                accountJpaRepository.save(accountDataAccessMapper.accountToAccountEntity(account)));
    }

    @Override
    public void editAccount(Account account) {
        accountJpaRepository.save(accountDataAccessMapper.accountToAccountEntity(account));
    }

    @Override
    public Optional<Account> findByAccountId(Long accountId) {
        return accountJpaRepository.findById(accountId)
                .map(accountDataAccessMapper::accountEntityToAccount);
    }}
