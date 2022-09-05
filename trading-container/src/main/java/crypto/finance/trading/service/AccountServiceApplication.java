package crypto.finance.trading.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "crypto.finance.trading.service.dataaccess"})
@EntityScan(basePackages = { "crypto.finance.trading.service.dataaccess" })
@SpringBootApplication(scanBasePackages = "crypto.finance.trading.service")
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

}
