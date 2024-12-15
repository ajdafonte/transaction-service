package pt.caires.transactionservice.api.mapper;

import org.springframework.stereotype.Component;
import pt.caires.transactionservice.api.dto.CreateTransactionResponseV1DTO;
import pt.caires.transactionservice.usecase.CreateTransactionResult;

@Component
public class CreateTransactionResponseMapper {

  public CreateTransactionResponseV1DTO map(CreateTransactionResult createTransactionResult) {
    return new CreateTransactionResponseV1DTO(
        createTransactionResult.status(),
        createTransactionResult.message(),
        createTransactionResult.fraudScore());
  }

}
