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
public class DetailAccountResponse {

    @NotNull
    private final Long accountId;
    @NotNull
    private final String name;
    @NotNull
    private final BigDecimal usd_balance;
    @NotNull
    private final BigDecimal btc;

}
