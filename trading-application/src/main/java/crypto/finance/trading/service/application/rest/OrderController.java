package crypto.finance.trading.service.application.rest;

import crypto.finance.trading.service.application.service.OrderService;
import crypto.finance.trading.service.domain.dto.create.order.CreateLimitOrderCommand;
import crypto.finance.trading.service.domain.dto.create.order.CreateOrderLImitResponse;
import crypto.finance.trading.service.domain.dto.details.order.DetailOrderQuery;
import crypto.finance.trading.service.domain.dto.details.order.DetailOrderResponse;
import crypto.finance.trading.service.domain.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    private final OrderApplicationService orderApplicationService;
    private final OrderService orderService;

    public OrderController(OrderApplicationService orderApplicationService, OrderService orderService) {
        this.orderApplicationService = orderApplicationService;
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<CreateOrderLImitResponse> createOrder(@RequestBody CreateLimitOrderCommand createLimitOrderCommand) {
        log.info("Creating Order limit with Price limit: {}", createLimitOrderCommand.getPriceLimit());
        CreateOrderLImitResponse response = orderApplicationService.createOrderLimit(createLimitOrderCommand);
        (new Thread(() -> {
            orderService.executeServer(createLimitOrderCommand.getPriceLimit(),response);
        })).start();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<DetailOrderResponse> fetchOrderDetails(@PathVariable Long orderId) {
        DetailOrderResponse detailOrderResponse =
                orderApplicationService.fetchOrderDetails(
                        DetailOrderQuery.builder().orderId(orderId).build());
        log.info("Returning order status with tracking id: {}", detailOrderResponse.getOrderId());
        return  ResponseEntity.ok(detailOrderResponse);
    }
}
