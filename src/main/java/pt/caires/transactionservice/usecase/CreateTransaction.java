package pt.caires.transactionservice.usecase;

import java.util.function.Function;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pt.caires.transactionservice.domain.exception.NotFoundServiceException;
import pt.caires.transactionservice.domain.terminal.Terminal;
import pt.caires.transactionservice.domain.terminal.TerminalFinder;
import pt.caires.transactionservice.domain.transaction.TransactionService;

@Service
public class CreateTransaction {

  private static final Logger LOGGER = LoggerFactory.getLogger(CreateTransaction.class);

  private final TerminalFinder terminalFinder;
  private final TransactionService transactionService;

  public CreateTransaction(TerminalFinder terminalFinder, TransactionService transactionService) {
    this.terminalFinder = terminalFinder;
    this.transactionService = transactionService;
  }

  public CreateTransactionResult execute(CreateTransactionRequest request) {
    var terminalId = request.terminalId();
    LOGGER.info("Attempt to create transaction for terminal {}", terminalId);

    return terminalFinder.findBy(terminalId, request.terminalThreatScore())
        .map(createTransaction(request))
        .orElseThrow(
            () -> new NotFoundServiceException("Unknown terminal " + terminalId));
  }

  private Function<Terminal, CreateTransactionResult> createTransaction(
      CreateTransactionRequest request) {
    return terminal -> {
      var transaction = transactionService.createTransaction(terminal, request.amount(),
          request.cardNumber());

      var createTransactionResult = new CreateTransactionResult(
          transaction.getStatus(),
          transaction.getMessage(),
          transaction.getFraudScore());
      LOGGER.info("Created transaction with following result {}", createTransactionResult);
      return createTransactionResult;
    };
  }

}
