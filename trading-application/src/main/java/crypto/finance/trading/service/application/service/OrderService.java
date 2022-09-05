package crypto.finance.trading.service.application.service;

import crypto.finance.trading.service.domain.dto.create.order.CreateOrderLImitResponse;

import java.math.BigDecimal;

public interface OrderService {

    void executeServer(BigDecimal priceLimit, CreateOrderLImitResponse createOrderLImitResponse);
}
