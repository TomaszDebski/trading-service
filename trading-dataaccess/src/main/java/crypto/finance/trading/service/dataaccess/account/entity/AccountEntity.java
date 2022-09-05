package crypto.finance.trading.service.dataaccess.account.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
@Entity
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long account_Id;

    private String name;
    private BigDecimal usd_balance;
    private BigDecimal btc;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<OrderEntity> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return Objects.equals(account_Id, that.account_Id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account_Id, name);
    }

}
