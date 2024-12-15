package pt.caires.transactionservice.usecase;

import org.springframework.stereotype.Service;
import pt.caires.transactionservice.domain.TerminalFinder;
import pt.caires.transactionservice.domain.exception.NotFoundServiceException;

@Service
public class CreateTransaction {

  private final TerminalFinder terminalFinder;

  public CreateTransaction(TerminalFinder terminalFinder) {
    this.terminalFinder = terminalFinder;
  }

  public CreateTransactionResult execute(CreateTransactionRequest request) {
    if (terminalFinder.findBy(request.terminalId(), request.terminalThreatScore()).isEmpty()) {
      throw new NotFoundServiceException("Unknown terminal " + request.terminalId());
    }

    return new CreateTransactionResult("success",
        "Transaction created",
        25);
  }

}
