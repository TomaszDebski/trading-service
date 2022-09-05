package crypto.finance.trading.service.dataaccess.account.mapper;

import crypto.finance.trading.service.dataaccess.account.entity.AccountEntity;
import crypto.finance.trading.service.dataaccess.account.entity.OrderEntity;
import crypto.finance.trading.service.domain.entity.Order;
import crypto.finance.trading.service.domain.valueobject.AccountId;
import crypto.finance.trading.service.domain.valueobject.OrderId;
import org.springframework.stereotype.Component;

@Component
public class OrderDataAccessMapper {

    public Order orderEntityToOrder(OrderEntity orderEntity) {
        return Order.builder()
                .orderId(new OrderId(orderEntity.getId()))
                .accountId(new AccountId(orderEntity.getAccount().getAccount_Id()))
                .priceLimit(orderEntity.getPriceLimit())
                .orderLimitStatus(orderEntity.getOrderLimitStatus())
                .amount(orderEntity.getAmount())
                .build();
    }

    public OrderEntity orderToOrderEntity(Order order, AccountEntity accountEntity) {
        return OrderEntity.builder()
                .id(order.getId() != null ? order.getId().getValue(): null)
                .account(accountEntity)
                .amount(order.getAmount())
                .priceLimit(order.getPriceLimit())
                .orderLimitStatus(order.getOrderLimitStatus())
                .processingTime(order.getProcessedTime())
                .build();
    }

}
