package crypto.finance.trading.service.domain.dto.details.order;

import crypto.finance.trading.service.domain.valueobject.OrderLimitStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class DetailOrderResponse {
    @NotNull
    private final Long orderId;

    @NotNull
    private final Long accountId;
    @NotNull
    private final OrderLimitStatus orderStatus;
    @NotNull
    private final BigDecimal amount;
    @NotNull
    private final BigDecimal priceLimit;


}
