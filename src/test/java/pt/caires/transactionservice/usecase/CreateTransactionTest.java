package pt.caires.transactionservice.usecase;

import static org.assertj.core.api.BDDAssertions.then;
import java.util.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.caires.transactionservice.domain.Amount;

class CreateTransactionTest {

  private CreateTransaction createTransaction;

  @BeforeEach
  void setUp() {
    this.createTransaction = new CreateTransaction();
  }

  @Test
  void shouldCreateTransaction() {
    var request =
        new CreateTransactionRequest("e3211be6-d0cc-4718-905d-ab933cc91ecb", 50,
            new Amount(50, Currency.getInstance("DKK")),
            "4100000099998888");
    var expected = new CreateTransactionResult("success",
        "Transaction created",
        25);

    var result = createTransaction.execute(request);

    then(result)
        .isNotNull()
        .isEqualTo(expected);
  }

}