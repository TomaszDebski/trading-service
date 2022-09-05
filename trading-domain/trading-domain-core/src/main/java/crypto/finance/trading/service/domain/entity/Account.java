package crypto.finance.trading.service.domain.entity;

import crypto.finance.trading.service.domain.valueobject.AccountId;

import java.math.BigDecimal;

public class Account extends AggregateRoot<AccountId> {

    private final String name;

    private BigDecimal usd_balance;

    private BigDecimal btc;

    private Account(Builder builder) {
        super.setId(builder.accountId);
        name = builder.name;
        usd_balance = builder.usd_balance;
        btc = builder.btc;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void calculateBTC(BigDecimal amount, BigDecimal price ) {
        btc = amount;
        usd_balance = usd_balance.subtract(amount.multiply(price));
    }

    public String getName() {
        return name;
    }

    public BigDecimal getUsd_balance() {
        return usd_balance;
    }

    public BigDecimal getBtc() {
        return btc;
    }


    public static final class Builder {
        private  String name;
        private BigDecimal usd_balance;
        private BigDecimal btc;
        private AccountId accountId;

        private Builder() {
        }

        public Builder(Account copy) {
            this.name = copy.getName();
            this.usd_balance = copy.getUsd_balance();
            this.btc = copy.getBtc();
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder usd_balance(BigDecimal val) {
            usd_balance = val;
            return this;
        }

        public Builder btc(BigDecimal val) {
            btc = val;
            return this;
        }

        public Builder accountId(AccountId val) {
            accountId = val;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}


