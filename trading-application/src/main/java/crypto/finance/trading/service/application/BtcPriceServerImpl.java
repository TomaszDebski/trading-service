package crypto.finance.trading.service.application;

import crypto.finance.trading.service.domain.dto.details.BtcPriceResponse;
import crypto.finance.trading.service.application.exception.ServerException;
import crypto.finance.trading.service.application.service.OrderService;
import crypto.finance.trading.service.domain.dto.create.order.CreateOrderLImitResponse;
import crypto.finance.trading.service.domain.ports.input.service.AccountApplicationService;
import crypto.finance.trading.service.domain.ports.input.service.OrderApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.Duration;

@Component
@Slf4j
public class BtcPriceServerImpl implements OrderService {

    public static final String BASE_URL = "http://127.0.0.1:5000";
    public static final String BTC_PRICE = "/btc-price";
    public static final String ERROR = "error";

    private final OrderApplicationService orderApplicationService;

    private final AccountApplicationService accountApplicationService;

    public BtcPriceServerImpl(OrderApplicationService orderApplicationService,
                              AccountApplicationService accountApplicationService) {
        this.orderApplicationService = orderApplicationService;
        this.accountApplicationService = accountApplicationService;
    }

    @Override
    public void executeServer(BigDecimal priceLimit, CreateOrderLImitResponse createOrderLImitResponse) {
        WebClient client = WebClient.create(BASE_URL);
        BtcPriceResponse btcPriceResponse = client.get()
                .uri(BTC_PRICE)
                .retrieve()
                .bodyToMono(BtcPriceResponse.class)
                .repeatWhen(longFlux -> Flux.interval(Duration.ofSeconds(1)))
                .takeUntil(element -> element.getPrice().compareTo(priceLimit) < 0)
                .log()
                .onErrorReturn(BtcPriceResponse.builder().message(ERROR).build())
                .last().block();
        if (ERROR.equals(btcPriceResponse.getMessage())){
            log.error("Connection refused: {}", btcPriceResponse.getMessage());
            throw new ServerException("Connection refused " +
                    btcPriceResponse.getMessage());
        }else{
            accountApplicationService.editAccount(createOrderLImitResponse.getAccountId(),
                    createOrderLImitResponse.getAmount(),btcPriceResponse);
            orderApplicationService.editOrder(createOrderLImitResponse.getOrderId());
        }
    }
}
