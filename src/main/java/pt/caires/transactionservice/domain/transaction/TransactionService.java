package pt.caires.transactionservice.domain.transaction;

import java.util.Currency;
import org.springframework.stereotype.Service;
import pt.caires.transactionservice.domain.Amount;
import pt.caires.transactionservice.domain.terminal.Terminal;

@Service
public class TransactionService {

  private static final int THREAT_SCORE_LIMIT_DK = 50;
  private static final Amount AMOUNT_LIMIT_FOR_THREAT_SCORE_DK =
      new Amount(500, Currency.getInstance("DKK"));

  private final TransactionRepository transactionRepository;

  public TransactionService(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  private static boolean isAmountTooLargeForThreatScore(Terminal terminal, Amount amount) {
    return terminal.getThreatScore() > THREAT_SCORE_LIMIT_DK &&
           amount.isHigherThan(AMOUNT_LIMIT_FOR_THREAT_SCORE_DK);
  }

  public Transaction createTransaction(Terminal terminal, Amount amount, String cardNumber) {
    var terminalId = terminal.getId();
    var transaction = isAmountTooLargeForThreatScore(terminal, amount) ?
        Transaction.createFailure("id", terminalId, amount, cardNumber) :
        Transaction.createSuccess("id", terminalId, amount, cardNumber);

    transactionRepository.save(transaction);

    return transaction;
  }

}
