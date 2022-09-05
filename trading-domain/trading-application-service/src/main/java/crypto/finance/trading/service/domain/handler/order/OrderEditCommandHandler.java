package crypto.finance.trading.service.domain.handler.order;

import crypto.finance.trading.service.domain.entity.Order;
import crypto.finance.trading.service.domain.exception.AccountDomainException;
import crypto.finance.trading.service.domain.exception.OrderDomainException;
import crypto.finance.trading.service.domain.mapper.OrderDataMapper;
import crypto.finance.trading.service.domain.ports.output.repository.OrderRepository;
import crypto.finance.trading.service.domain.valueobject.OrderLimitStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class OrderEditCommandHandler {

    private final OrderRepository orderRepository;

    private final OrderDataMapper orderDataMapper;

    public OrderEditCommandHandler(OrderRepository orderRepository,
                                   OrderDataMapper orderDataMapper, OrderQueryHandler orderQueryHandler) {
        this.orderRepository = orderRepository;
        this.orderDataMapper = orderDataMapper;
    }

        @Transactional
    public void editOrder(Long orderId, OrderLimitStatus orderLimitStatus) {
        Optional<Order> orderResult =
                orderRepository.findByOrderId(orderId);
        if(orderResult.isEmpty()) {
            log.warn("Could not find order with id: {}", orderId);
            throw new OrderDomainException("Could not find order with id: " + orderId);
        }
        Order order = orderDataMapper.updateOrder(orderResult.get(), orderLimitStatus);
        orderRepository.editOrder(order);
    }
}
