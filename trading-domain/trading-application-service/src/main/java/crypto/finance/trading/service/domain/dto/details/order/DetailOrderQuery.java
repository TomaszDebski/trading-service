package crypto.finance.trading.service.domain.dto.details.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class DetailOrderQuery {
    @NotNull
//    private final UUID accountId;
    private final Long orderId;
//    @NotNull
    private final String name;
//    @NotNull
    private final BigDecimal usd_balance;

    private final BigDecimal btc;
}
