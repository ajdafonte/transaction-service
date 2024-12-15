package pt.caires.transactionservice.api.mapper;

import static org.assertj.core.api.BDDAssertions.then;
import java.util.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.caires.transactionservice.api.dto.AmountDTO;
import pt.caires.transactionservice.api.dto.CreateTransactionRequestV1DTO;
import pt.caires.transactionservice.domain.Amount;
import pt.caires.transactionservice.usecase.CreateTransactionRequest;

class CreateTransactionRequestMapperTest {

  private static final Currency CURRENCY_DK = Currency.getInstance("DKK");

  private CreateTransactionRequestMapper mapper;

  @BeforeEach
  void setUp() {
    this.mapper = new CreateTransactionRequestMapper();
  }

  @Test
  void shouldMap() {
    var request = new CreateTransactionRequestV1DTO(
        "e3211be6-d0cc-4718-905d-ab933cc91ecb",
        50,
        new AmountDTO(50, "DKK"),
        "4100000099998888");
    var expected =
        new CreateTransactionRequest(
            "e3211be6-d0cc-4718-905d-ab933cc91ecb", 50,
            new Amount(50, CURRENCY_DK),
            "4100000099998888");

    var result = mapper.map(request);

    then(result)
        .isNotNull()
        .isEqualTo(expected);
  }

}