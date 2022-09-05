package crypto.finance.trading.service.domain.dto.details.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class DetailAccountQuery {
    @NotNull
    private final Long accountId;
}
