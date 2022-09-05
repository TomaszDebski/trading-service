package crypto.finance.trading.service.dataaccess.account.repository;

import crypto.finance.trading.service.dataaccess.account.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {
}
