package crypto.finance.trading.service.domain.mapper;

import crypto.finance.trading.service.domain.dto.create.order.CreateLimitOrderCommand;
import crypto.finance.trading.service.domain.dto.create.order.CreateOrderLImitResponse;
import crypto.finance.trading.service.domain.dto.details.BtcPriceResponse;
import crypto.finance.trading.service.domain.dto.details.order.DetailOrderQuery;
import crypto.finance.trading.service.domain.dto.details.order.DetailOrderResponse;
import crypto.finance.trading.service.domain.entity.Order;
import crypto.finance.trading.service.domain.valueobject.AccountId;
import crypto.finance.trading.service.domain.valueobject.OrderLimitStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class OrderDataMapper {

    public Order createLimitOrderCommandToOrder(CreateLimitOrderCommand createLimitOrderCommand) {
        return Order.builder()
                .accountId(new AccountId(createLimitOrderCommand.getAccountId()))
                .priceLimit(createLimitOrderCommand.getPriceLimit())
                .orderLimitStatus(OrderLimitStatus.PROCESSING)
                .amount(createLimitOrderCommand.getAmount())
                .build();
    }

    public CreateOrderLImitResponse orderToCreateOrderLImitResponse(Order order) {
        return CreateOrderLImitResponse.builder()
                .orderId(order.getId().getValue())
                .accountId(order.getAccountId().getValue())
                .amount(order.getAmount())
                .priceLimit(order.getPriceLimit())
                .orderStatus(order.getOrderLimitStatus())
                .build();
    }

    public DetailOrderResponse orderToDetailOrderResponse(Order order) {
        return DetailOrderResponse.builder()
                .orderId(order.getId().getValue())
                .amount(order.getAmount())
                .orderStatus(order.getOrderLimitStatus())
                .priceLimit(order.getPriceLimit())
                .accountId(order.getAccountId().getValue())
//                .failureMessages(account.getFailureMessages())
                .build();
    }

    public Order detailOrderResponseToOrder(DetailOrderResponse detailOrderResponse) {
        return Order.builder()
//                .orderId(detailOrderResponse.getOrderId())
//                .name(order.get)
//                .btc(order.getAmount())
//                .usd_balance(order.getAmount())
//                .orderTrackingId(account.getTrackingId().getValue())
//                .orderStatus(account.getOrderStatus())
//                .failureMessages(account.getFailureMessages())
                .build();
    }

    public Order updateOrder(Order order, OrderLimitStatus orderLimitStatus){
        return Order.builder()
                .orderId(order.getId())
                .orderLimitStatus(orderLimitStatus)
                .accountId(order.getAccountId())
                .priceLimit(order.getPriceLimit())
                .amount(order.getAmount())
                .processedTime(LocalDateTime.now())
                .build();
    }

}
