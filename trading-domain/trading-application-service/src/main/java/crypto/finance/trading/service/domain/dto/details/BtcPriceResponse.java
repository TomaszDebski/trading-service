package crypto.finance.trading.service.domain.dto.details;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BtcPriceResponse {

    private BigDecimal price;
    private LocalDateTime timestamp;
    private String message;

}
