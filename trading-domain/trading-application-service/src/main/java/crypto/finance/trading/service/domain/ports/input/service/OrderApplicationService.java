package crypto.finance.trading.service.domain.ports.input.service;

import crypto.finance.trading.service.domain.dto.create.order.CreateLimitOrderCommand;
import crypto.finance.trading.service.domain.dto.create.order.CreateOrderLImitResponse;
import crypto.finance.trading.service.domain.dto.details.order.DetailOrderQuery;
import crypto.finance.trading.service.domain.dto.details.order.DetailOrderResponse;

import javax.validation.Valid;

public interface OrderApplicationService {

    CreateOrderLImitResponse createOrderLimit(@Valid CreateLimitOrderCommand createLimitOrderCommand);

    void editOrder(Long orderId);

    DetailOrderResponse fetchOrderDetails(@Valid DetailOrderQuery detailOrderQuery);
}
