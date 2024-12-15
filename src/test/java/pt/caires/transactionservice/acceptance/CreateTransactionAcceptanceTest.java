package pt.caires.transactionservice.acceptance;

import static org.hamcrest.Matchers.is;
import java.net.HttpURLConnection;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateTransactionAcceptanceTest extends BaseAcceptanceTest {

  private static final String VALID_TERMINAL_ID = "e3211be6-d0cc-4718-905d-ab933cc91ecb";

  private static String buildCreateTransactionRequestBody(String terminalId,
      int terminalThreatScore,
      long amountValue) {
    return String.format(
        // language=JSON
        """
            {
              "terminalId": "%s",
              "terminalThreatScore": "%d",
              "amount": {
                "currency": "DKK",
                "value": "%d"
              },
              "cardNumber": "4100000099998888"
            }""", terminalId, terminalThreatScore, amountValue);
  }

  @Test
  void shouldCreateTransaction() {

    // language=JSON
    var expectedResponse = """
        {"status":"success","message":"Transaction created","fraudScore":0}""";

    RestAssured.given()
        .header(HttpHeaders.CONTENT_TYPE, ContentType.JSON)
        .body(buildCreateTransactionRequestBody(VALID_TERMINAL_ID, 50, 50))
        .post("api/v1/transactions")
        .then()
        .statusCode(HttpURLConnection.HTTP_CREATED)
        .contentType(ContentType.JSON)
        .body(is(expectedResponse));
  }

  @Test
  void shouldNotCreateTransactionWhenUnknownTerminal() {
    var terminalId = "dummyTerminalId";

    RestAssured.given()
        .header(HttpHeaders.CONTENT_TYPE, ContentType.JSON)
        .body(buildCreateTransactionRequestBody(terminalId, 50, 50))
        .post("api/v1/transactions")
        .then()
        .statusCode(HttpURLConnection.HTTP_NOT_FOUND)
        .contentType(ContentType.JSON);
  }

  @Test
  void shouldCreateTransactionWithFailureWhenAmountTooLargeForTerminalThreatScore() {
    // language=JSON
    var expectedResponse = """
        {"status":"failure","message":"Amount too large for terminal threat score","fraudScore":25}""";

    RestAssured.given()
        .header(HttpHeaders.CONTENT_TYPE, ContentType.JSON)
        .body(buildCreateTransactionRequestBody(VALID_TERMINAL_ID, 75, 750))
        .post("api/v1/transactions")
        .then()
        .statusCode(HttpURLConnection.HTTP_CREATED)
        .contentType(ContentType.JSON)
        .body(is(expectedResponse));
  }

}
