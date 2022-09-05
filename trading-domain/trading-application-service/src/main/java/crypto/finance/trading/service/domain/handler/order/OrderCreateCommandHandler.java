package crypto.finance.trading.service.domain.handler.order;

import crypto.finance.trading.service.domain.dto.create.order.CreateLimitOrderCommand;
import crypto.finance.trading.service.domain.entity.Order;
import crypto.finance.trading.service.domain.exception.OrderDomainException;
import crypto.finance.trading.service.domain.mapper.OrderDataMapper;
import crypto.finance.trading.service.domain.ports.output.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class OrderCreateCommandHandler {

    private final OrderRepository orderRepository;

    private final OrderDataMapper orderDataMapper;

    public OrderCreateCommandHandler(OrderRepository orderRepository,
                                     OrderDataMapper orderDataMapper) {
        this.orderRepository = orderRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Transactional
    public Order createOrder(CreateLimitOrderCommand createLimitOrderCommand) {
        Order order = orderDataMapper.createLimitOrderCommandToOrder(createLimitOrderCommand);
        Order savedOrder = orderRepository.createOrder(order);
        if (savedOrder == null) {
            log.error("Could not save Order for account id: {}", createLimitOrderCommand.getAccountId());
            throw new OrderDomainException("Could not save Order for account id " +
                    createLimitOrderCommand.getAccountId());
        }
        return savedOrder;
    }
}
