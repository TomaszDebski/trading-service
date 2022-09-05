package crypto.finance.trading.service.dataaccess.account.mapper;

import crypto.finance.trading.service.dataaccess.account.entity.AccountEntity;
import crypto.finance.trading.service.domain.entity.Account;
import crypto.finance.trading.service.domain.valueobject.AccountId;
import org.springframework.stereotype.Component;

@Component
public class AccountDataAccessMapper {

    public Account accountEntityToAccount(AccountEntity customerEntity) {
        return Account.builder()
                .accountId(new AccountId(customerEntity.getAccount_Id()))
                .name(customerEntity.getName())
                .btc(customerEntity.getBtc())
                .usd_balance(customerEntity.getUsd_balance())
                        .build();
    }

    public AccountEntity accountToAccountEntity(Account account) {
        return AccountEntity.builder()
                .account_Id(account.getId() != null ? account.getId().getValue(): null)
                .name(account.getName())
                .usd_balance(account.getUsd_balance())
                .btc(account.getBtc())
                .build();
    }

}
