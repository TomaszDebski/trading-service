package crypto.finance.trading.service.domain.dto.create.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateAccountCommand {

    @NotNull
    private final String name;

    @NotNull
    private final BigDecimal usd_balance;

//    private final BigDecimal btc;
}
