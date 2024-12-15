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
import pt.caires.transactionservice.api.mapper.CreateTransactionRequestMapper;
import pt.caires.transactionservice.api.mapper.CreateTransactionResponseMapper;
import pt.caires.transactionservice.usecase.CreateTransaction;

@RestController
@RequestMapping(value = "api/v1/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionV1Controller {

  private final CreateTransaction createTransaction;
  private final CreateTransactionRequestMapper createTransactionRequestMapper;
  private final CreateTransactionResponseMapper createTransactionResponseMapper;

  public TransactionV1Controller(CreateTransaction createTransaction,
      CreateTransactionRequestMapper createTransactionRequestMapper,
      CreateTransactionResponseMapper createTransactionResponseMapper) {
    this.createTransaction = createTransaction;
    this.createTransactionRequestMapper = createTransactionRequestMapper;
    this.createTransactionResponseMapper = createTransactionResponseMapper;
  }

  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public CreateTransactionResponseV1DTO create(@RequestBody CreateTransactionRequestV1DTO request) {
    return createTransactionResponseMapper.map(
        createTransaction.execute(createTransactionRequestMapper.map(request)));
  }

}
