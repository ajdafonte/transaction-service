package pt.caires.transactionservice.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pt.caires.transactionservice.api.dto.CreateTransactionRequestV1DTO;
import pt.caires.transactionservice.api.dto.CreateTransactionResponseV1DTO;

@RestController
@RequestMapping(value = "api/v1/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionV1Controller {

  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public CreateTransactionResponseV1DTO create(@RequestBody CreateTransactionRequestV1DTO request) {
    return new CreateTransactionResponseV1DTO("success",
        "Transaction created",
        25);
  }

}
