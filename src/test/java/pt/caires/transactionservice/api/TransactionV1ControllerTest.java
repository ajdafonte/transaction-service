package pt.caires.transactionservice.api;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import java.util.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pt.caires.transactionservice.api.dto.AmountDTO;
import pt.caires.transactionservice.api.dto.CreateTransactionRequestV1DTO;
import pt.caires.transactionservice.api.dto.CreateTransactionResponseV1DTO;
import pt.caires.transactionservice.api.mapper.CreateTransactionRequestMapper;
import pt.caires.transactionservice.api.mapper.CreateTransactionResponseMapper;
import pt.caires.transactionservice.domain.Amount;
import pt.caires.transactionservice.usecase.CreateTransaction;
import pt.caires.transactionservice.usecase.CreateTransactionRequest;
import pt.caires.transactionservice.usecase.CreateTransactionResult;

@ExtendWith(MockitoExtension.class)
class TransactionV1ControllerTest {

  private static final Currency CURRENCY_DK = Currency.getInstance("DKK");
  private static final Amount AMOUNT = new Amount(50, CURRENCY_DK);
  private static final String CARD_NUMBER = "4100000099998888";

  @Mock
  private CreateTransaction createTransaction;
  @Mock
  private CreateTransactionRequestMapper createTransactionRequestMapper;
  @Mock
  private CreateTransactionResponseMapper createTransactionResponseMapper;

  private TransactionV1Controller controller;

  @BeforeEach
  void setUp() {
    this.controller = new TransactionV1Controller(createTransaction,
        createTransactionRequestMapper,
        createTransactionResponseMapper);
  }

  @Test
  void shouldCreateTransaction() {
    var request = new CreateTransactionRequestV1DTO(
        "e3211be6-d0cc-4718-905d-ab933cc91ecb",
        50,
        new AmountDTO(50, "DKK"),
        "4100000099998888");
    var createTransactionRequest = new CreateTransactionRequest(
        "e3211be6-d0cc-4718-905d-ab933cc91ecb", 50, AMOUNT, CARD_NUMBER);
    var createTransactionResult = new CreateTransactionResult("success",
        "Transaction created",
        25);
    var expected = new CreateTransactionResponseV1DTO("success",
        "Transaction created",
        25);
    given(createTransactionRequestMapper.map(request)).willReturn(createTransactionRequest);
    given(createTransaction.execute(createTransactionRequest)).willReturn(createTransactionResult);
    given(createTransactionResponseMapper.map(createTransactionResult)).willReturn(expected);

    var result = controller.create(request);

    then(result)
        .isNotNull()
        .isEqualTo(expected);
  }

}