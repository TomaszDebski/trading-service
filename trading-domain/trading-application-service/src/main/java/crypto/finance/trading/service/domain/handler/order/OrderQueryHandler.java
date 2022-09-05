package crypto.finance.trading.service.domain.handler.order;

import crypto.finance.trading.service.domain.dto.details.order.DetailOrderQuery;
import crypto.finance.trading.service.domain.dto.details.order.DetailOrderResponse;
import crypto.finance.trading.service.domain.entity.Order;
import crypto.finance.trading.service.domain.exception.OrderDomainException;
import crypto.finance.trading.service.domain.mapper.AccountDataMapper;
import crypto.finance.trading.service.domain.mapper.OrderDataMapper;
import crypto.finance.trading.service.domain.ports.output.repository.AccountRepository;
import crypto.finance.trading.service.domain.ports.output.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class OrderQueryHandler {

    private final OrderDataMapper orderDataMapper;

    private final OrderRepository orderRepository;

    public OrderQueryHandler(OrderDataMapper orderDataMapper,
                             OrderRepository orderRepository) {
        this.orderDataMapper = orderDataMapper;
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public DetailOrderResponse fetchOrder(DetailOrderQuery detailOrderQuery) {
           Optional<Order> orderResult =
                   orderRepository.findByOrderId(detailOrderQuery.getOrderId());
           if (orderResult.isEmpty()) {
               log.warn("Could not find order with id: {}", detailOrderQuery.getOrderId());
               throw new OrderDomainException("Could not find order with id: " +
                       detailOrderQuery.getOrderId());
           }
           return orderDataMapper.orderToDetailOrderResponse(orderResult.get());
    }
}
