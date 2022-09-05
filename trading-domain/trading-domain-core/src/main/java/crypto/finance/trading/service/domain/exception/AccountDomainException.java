package crypto.finance.trading.service.domain.exception;

public class AccountDomainException extends RuntimeException {

    public AccountDomainException(String message) {
        super(message);
    }

    public AccountDomainException(String message, Throwable cause) {
        super(message, cause);
    }

}
