package pt.caires.transactionservice.api.mapper;

import java.util.Currency;
import org.springframework.stereotype.Component;
import pt.caires.transactionservice.api.dto.CreateTransactionRequestV1DTO;
import pt.caires.transactionservice.domain.Amount;
import pt.caires.transactionservice.usecase.CreateTransactionRequest;

@Component
public class CreateTransactionRequestMapper {

  public CreateTransactionRequest map(CreateTransactionRequestV1DTO request) {
    var amountDTO = request.amount();
    var amount = new Amount(amountDTO.value(), Currency.getInstance(amountDTO.currency()));

    return new CreateTransactionRequest(request.terminalId(),
        request.terminalThreatScore(),
        amount,
        request.cardNumber());
  }

}
