package crypto.finance.trading.service.domain.dto.create.order;

import crypto.finance.trading.service.domain.valueobject.OrderLimitStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class CreateLimitOrderCommand {
    @NotNull
    private Long accountId;
    @NotNull
    private BigDecimal priceLimit;
    @NotNull
    private final BigDecimal amount;
}
