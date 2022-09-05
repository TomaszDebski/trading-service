package crypto.finance.trading.service.domain.entity;

import crypto.finance.trading.service.domain.valueobject.AccountId;
import crypto.finance.trading.service.domain.valueobject.OrderId;
import crypto.finance.trading.service.domain.valueobject.OrderLimitStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order extends AggregateRoot<OrderId> {

    private AccountId accountId;

    private BigDecimal priceLimit;

    private OrderLimitStatus orderLimitStatus;

    private final BigDecimal amount;

    private LocalDateTime processedTime;

    public static Builder builder() {
        return new Builder();
    }

    public BigDecimal getPriceLimit() {
        return priceLimit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public OrderLimitStatus getOrderLimitStatus() {
        return orderLimitStatus;
    }

    public LocalDateTime getProcessedTime() {
        return processedTime;
    }

    public AccountId getAccountId() {
        return accountId;
    }

    private Order(Builder builder) {
        super.setId(builder.orderId);
        this.accountId = builder.accountId;
        this.priceLimit = builder.priceLimit;
        this.orderLimitStatus = builder.orderLimitStatus;
        this.amount = builder.amount;
        this.processedTime = builder.processedTime;
    }


    public static final class Builder {
        private OrderId orderId;
        private AccountId accountId;
        private BigDecimal priceLimit;
        private OrderLimitStatus orderLimitStatus;
        private BigDecimal amount;
        private LocalDateTime processedTime;

        private Builder() {
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder accountId(AccountId val) {
            accountId = val;
            return this;
        }

        public Builder priceLimit(BigDecimal val) {
            priceLimit = val;
            return this;
        }
        public Builder amount(BigDecimal val) {
            amount = val;
            return this;
        }

        public Builder orderLimitStatus(OrderLimitStatus val) {
            orderLimitStatus = val;
            return this;
        }

        public Builder processedTime(LocalDateTime val) {
            processedTime = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }

    }
}


