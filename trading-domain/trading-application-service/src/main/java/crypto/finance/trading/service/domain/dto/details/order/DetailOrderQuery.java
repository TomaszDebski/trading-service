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
    private final Long orderId;

}
