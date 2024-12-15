package pt.caires.transactionservice.usecase;

import org.springframework.stereotype.Service;

@Service
public class CreateTransaction {

  public CreateTransactionResult execute(CreateTransactionRequest request) {
    return new CreateTransactionResult("success",
        "Transaction created",
        25);
  }

}
