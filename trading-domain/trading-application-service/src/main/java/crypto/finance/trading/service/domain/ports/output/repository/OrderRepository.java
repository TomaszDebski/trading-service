package crypto.finance.trading.service.domain.ports.output.repository;

import crypto.finance.trading.service.domain.entity.Order;

import java.util.Optional;

public interface OrderRepository {

    Order createOrder(Order order);

    void editOrder(Order order);

    Optional<Order> findByOrderId(Long orderId);
}
