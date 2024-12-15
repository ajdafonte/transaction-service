package pt.caires.transactionservice.usecase;

import java.util.function.Function;
import org.springframework.stereotype.Service;
import pt.caires.transactionservice.domain.exception.NotFoundServiceException;
import pt.caires.transactionservice.domain.terminal.Terminal;
import pt.caires.transactionservice.domain.terminal.TerminalFinder;
import pt.caires.transactionservice.domain.transaction.TransactionService;

@Service
public class CreateTransaction {

  private final TerminalFinder terminalFinder;
  private final TransactionService transactionService;

  public CreateTransaction(TerminalFinder terminalFinder, TransactionService transactionService) {
    this.terminalFinder = terminalFinder;
    this.transactionService = transactionService;
  }

  public CreateTransactionResult execute(CreateTransactionRequest request) {
    return terminalFinder.findBy(request.terminalId(), request.terminalThreatScore())
        .map(createTransaction(request))
        .orElseThrow(
            () -> new NotFoundServiceException("Unknown terminal " + request.terminalId()));
  }

  private Function<Terminal, CreateTransactionResult> createTransaction(
      CreateTransactionRequest request) {
    return terminal -> {
      var transaction = transactionService.createTransaction(terminal, request.amount(),
          request.cardNumber());
      return new CreateTransactionResult(
          transaction.getStatus(),
          transaction.getMessage(),
          transaction.getFraudScore());
    };
  }

}
