package pt.caires.transactionservice.domain;

import static org.assertj.core.api.BDDAssertions.then;
import java.util.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.caires.transactionservice.domain.terminal.Terminal;
import pt.caires.transactionservice.domain.transaction.Transaction;
import pt.caires.transactionservice.domain.transaction.TransactionRepository;
import pt.caires.transactionservice.domain.transaction.TransactionService;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

  private static final Currency CURRENCY_DK = Currency.getInstance("DKK");
  private static final String CARD_NUMBER = "cardNumber";
  private static final String TERMINAL_ID = "terminalId";

  @Mock
  private TransactionRepository transactionRepository;

  private TransactionService transactionService;

  @BeforeEach
  void setUp() {
    this.transactionService = new TransactionService(transactionRepository);
  }

  @Test
  void shouldCreateTransaction() {
    var terminal = new Terminal(TERMINAL_ID, 50);
    var amount = new Amount(50, CURRENCY_DK);
    var expected =
        new Transaction("id",
            TERMINAL_ID,
            amount,
            CARD_NUMBER,
            "success",
            "Transaction created",
            0);

    var result = transactionService.createTransaction(terminal, amount, CARD_NUMBER);

    then(result)
        .isNotNull()
        .isEqualTo(expected);
    BDDMockito.then(transactionRepository)
        .should()
        .save(expected);
  }

  @Test
  void shouldCreateInvalidTransactionWhenAmountTooLargeGivenTerminalThreatScore() {
    var terminal = new Terminal(TERMINAL_ID, 75);
    var amount = new Amount(1000, CURRENCY_DK);
    var expected = new Transaction("id",
        TERMINAL_ID,
        amount,
        CARD_NUMBER,
        "failure",
        "Amount too large for terminal threat score",
        25);

    var result = transactionService.createTransaction(terminal, amount, CARD_NUMBER);

    then(result)
        .isNotNull()
        .isEqualTo(expected);
    BDDMockito.then(transactionRepository)
        .should()
        .save(expected);
  }

}