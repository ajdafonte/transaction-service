package pt.caires.transactionservice.api.mapper;

import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.caires.transactionservice.api.dto.CreateTransactionResponseV1DTO;
import pt.caires.transactionservice.usecase.CreateTransactionResult;

class CreateTransactionResponseMapperTest {

  private CreateTransactionResponseMapper mapper;

  @BeforeEach
  void setUp() {
    this.mapper = new CreateTransactionResponseMapper();
  }

  @Test
  void shouldMap() {
    var createTransactionResult = new CreateTransactionResult("success", "Transaction created", 25);
    var expected = new CreateTransactionResponseV1DTO("success", "Transaction created", 25);

    var result = mapper.map(createTransactionResult);

    then(result)
        .isNotNull()
        .isEqualTo(expected);
  }

}