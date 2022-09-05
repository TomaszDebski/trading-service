package crypto.finance.trading.service.application.rest;

import crypto.finance.trading.service.domain.dto.create.account.CreateAccountCommand;
import crypto.finance.trading.service.domain.dto.create.account.CreateAccountResponse;
import crypto.finance.trading.service.domain.dto.details.account.DetailAccountQuery;
import crypto.finance.trading.service.domain.dto.details.account.DetailAccountResponse;
import crypto.finance.trading.service.domain.ports.input.service.AccountApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping(value = "/account")
public class AccountController {

    private final AccountApplicationService accountApplicationService;

    public AccountController(AccountApplicationService accountApplicationService) {
        this.accountApplicationService = accountApplicationService;
    }

    @PostMapping
    public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody CreateAccountCommand createAccountCommand) {
        log.info("Creating account with name: {}", createAccountCommand.getName());
        CreateAccountResponse response = accountApplicationService.createAccount(createAccountCommand);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<DetailAccountResponse> fetchAccountDetails(@PathVariable Long accountId) {
        DetailAccountResponse trackOrderResponse =
                accountApplicationService.fetchAccountDetails(
                        DetailAccountQuery.builder().accountId(accountId).build());
        log.info("Returning account with id: {}", trackOrderResponse.getAccountId());
        return  ResponseEntity.ok(trackOrderResponse);
    }
}
