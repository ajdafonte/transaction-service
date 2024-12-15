package pt.caires.transactionservice.api;

import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.caires.transactionservice.api.dto.AmountDTO;
import pt.caires.transactionservice.api.dto.CreateTransactionRequestV1DTO;
import pt.caires.transactionservice.api.dto.CreateTransactionResponseV1DTO;

class TransactionV1ControllerTest {

  private TransactionV1Controller controller;

  @BeforeEach
  void setUp() {
    this.controller = new TransactionV1Controller();
  }

  @Test
  void shouldCreateTransaction() {
    var request = new CreateTransactionRequestV1DTO(
        "e3211be6-d0cc-4718-905d-ab933cc91ecb",
        50,
        new AmountDTO(50, "DKK"),
        "4100000099998888");
    var expected = new CreateTransactionResponseV1DTO("success",
        "Transaction created",
        25);

    var result = controller.create(request);

    then(result)
        .isNotNull()
        .isEqualTo(expected);
  }

}