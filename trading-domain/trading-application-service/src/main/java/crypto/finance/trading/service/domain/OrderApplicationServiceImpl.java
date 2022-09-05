package crypto.finance.trading.service.domain;

import crypto.finance.trading.service.domain.dto.create.order.CreateLimitOrderCommand;
import crypto.finance.trading.service.domain.dto.create.order.CreateOrderLImitResponse;
import crypto.finance.trading.service.domain.dto.details.order.DetailOrderQuery;
import crypto.finance.trading.service.domain.dto.details.order.DetailOrderResponse;
import crypto.finance.trading.service.domain.entity.Order;
import crypto.finance.trading.service.domain.handler.account.AccountQueryHandler;
import crypto.finance.trading.service.domain.handler.order.OrderCreateCommandHandler;
import crypto.finance.trading.service.domain.handler.order.OrderEditCommandHandler;
import crypto.finance.trading.service.domain.handler.order.OrderQueryHandler;
import crypto.finance.trading.service.domain.mapper.OrderDataMapper;
import crypto.finance.trading.service.domain.ports.input.service.OrderApplicationService;
import crypto.finance.trading.service.domain.valueobject.OrderLimitStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@Service
public class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderCreateCommandHandler orderCreateCommandHandler;

    private final OrderEditCommandHandler orderEditCommandHandler;

    private final OrderDataMapper orderDataMapper;

    private final OrderQueryHandler orderQueryHandler;

    public OrderApplicationServiceImpl(OrderCreateCommandHandler orderCreateCommandHandler,
                                       OrderEditCommandHandler orderEditCommandHandler,
                                       OrderDataMapper orderDataMapper,
                                       OrderQueryHandler orderQueryHandler) {
        this.orderCreateCommandHandler = orderCreateCommandHandler;
        this.orderEditCommandHandler = orderEditCommandHandler;
        this.orderDataMapper = orderDataMapper;
        this.orderQueryHandler = orderQueryHandler;
    }

    @Override
    public CreateOrderLImitResponse createOrderLimit(CreateLimitOrderCommand createLimitOrderCommand) {
        Order order = orderCreateCommandHandler.createOrder(createLimitOrderCommand);
        return orderDataMapper
                .orderToCreateOrderLImitResponse(order);
    }

    @Override
    public void editOrder(Long orderId) {
        orderEditCommandHandler.editOrder(orderId, OrderLimitStatus.PROCESSED);
    }

    @Override
    public DetailOrderResponse fetchOrderDetails(DetailOrderQuery detailOrderQuery) {
        return orderQueryHandler.fetchOrder(detailOrderQuery);
    }
}
