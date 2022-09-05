package crypto.finance.trading.service.dataaccess.account.adapter;


import crypto.finance.trading.service.dataaccess.account.entity.AccountEntity;
import crypto.finance.trading.service.dataaccess.account.mapper.OrderDataAccessMapper;
import crypto.finance.trading.service.dataaccess.account.repository.AccountJpaRepository;
import crypto.finance.trading.service.dataaccess.account.repository.OrderJpaRepository;
import crypto.finance.trading.service.domain.entity.Order;
import crypto.finance.trading.service.domain.exception.AccountDomainException;
import crypto.finance.trading.service.domain.ports.output.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    private final AccountJpaRepository accountJpaRepository;

    private final OrderDataAccessMapper orderDataAccessMapper;

    public OrderRepositoryImpl(
            OrderJpaRepository orderJpaRepository,
            AccountJpaRepository accountJpaRepository, OrderDataAccessMapper orderDataAccessMapper) {
        this.orderJpaRepository = orderJpaRepository;
        this.accountJpaRepository = accountJpaRepository;
        this.orderDataAccessMapper = orderDataAccessMapper;
    }

    @Override
    public Order createOrder(Order order) {
        return orderDataAccessMapper.orderEntityToOrder(
                orderJpaRepository.save(orderDataAccessMapper.orderToOrderEntity(order,findAccountEntity(order))));
    }

    @Override
    public void editOrder(Order order) {
        orderJpaRepository.save(orderDataAccessMapper.orderToOrderEntity(order,findAccountEntity(order)));
    }

    @Override
    public Optional<Order> findByOrderId(Long orderId) {
        return orderJpaRepository.findById(orderId)
                .map(orderDataAccessMapper::orderEntityToOrder);
    }

    private AccountEntity findAccountEntity(Order order){
        Optional<AccountEntity> account = accountJpaRepository.findById(order.getAccountId().getValue());
        if(account.isEmpty()){
            log.error("Could not find account with id: {}", order.getAccountId().getValue());
            throw new AccountDomainException("Could not find account with id " +
                    order.getAccountId().getValue());
        }
        return account.get();
    }

}
