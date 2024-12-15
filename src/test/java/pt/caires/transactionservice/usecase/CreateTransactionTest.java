package pt.caires.transactionservice.usecase;

import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import java.util.Currency;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.caires.transactionservice.domain.Amount;
import pt.caires.transactionservice.domain.Terminal;
import pt.caires.transactionservice.domain.TerminalFinder;
import pt.caires.transactionservice.domain.Transaction;
import pt.caires.transactionservice.domain.TransactionService;
import pt.caires.transactionservice.domain.exception.NotFoundServiceException;

@ExtendWith(MockitoExtension.class)
class CreateTransactionTest {

  private static final String TERMINAL_ID = "e3211be6-d0cc-4718-905d-ab933cc91ecb";
  private static final int TERMINAL_THREAT_SCORE = 50;
  private static final Terminal TERMINAL = new Terminal(TERMINAL_ID, TERMINAL_THREAT_SCORE);
  private static final Amount AMOUNT =
      new Amount(TERMINAL_THREAT_SCORE, Currency.getInstance("DKK"));
  private static final String CARD_NUMBER = "4100000099998888";

  @Mock
  private TerminalFinder terminalFinder;
  @Mock
  private TransactionService transactionService;

  private CreateTransaction createTransaction;

  @BeforeEach
  void setUp() {
    this.createTransaction = new CreateTransaction(terminalFinder, transactionService);
  }

  @Test
  void shouldCreateTransaction() {
    var request =
        new CreateTransactionRequest(TERMINAL_ID, TERMINAL_THREAT_SCORE, AMOUNT, CARD_NUMBER);
    var expected = new CreateTransactionResult("success", "Transaction created", 25);
    given(terminalFinder.findBy(TERMINAL_ID, TERMINAL_THREAT_SCORE))
        .willReturn(Optional.of(TERMINAL));
    given(transactionService.createTransaction(TERMINAL, AMOUNT, CARD_NUMBER))
        .willReturn(new Transaction("transactionId", TERMINAL_ID, AMOUNT, CARD_NUMBER, "success",
            "Transaction created", 25));

    var result = createTransaction.execute(request);

    then(result)
        .isNotNull()
        .isEqualTo(expected);
  }

  @Test
  void shouldFailToCreateTransactionWhenTerminalNotFound() {
    var request = new CreateTransactionRequest("unknownTerminalId",
        TERMINAL_THREAT_SCORE,
        AMOUNT,
        CARD_NUMBER);
    given(terminalFinder.findBy("unknownTerminalId", TERMINAL_THREAT_SCORE))
        .willReturn(Optional.empty());

    var throwable = catchThrowable(() -> createTransaction.execute(request));

    then(throwable)
        .isNotNull()
        .isInstanceOf(NotFoundServiceException.class)
        .hasMessage("Unknown terminal unknownTerminalId");
  }

}