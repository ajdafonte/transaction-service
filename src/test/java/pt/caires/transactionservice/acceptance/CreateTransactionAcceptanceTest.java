package pt.caires.transactionservice.acceptance;

import static org.hamcrest.Matchers.is;
import java.net.HttpURLConnection;
import org.apache.http.HttpHeaders;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateTransactionAcceptanceTest extends BaseAcceptanceTest {

  private static String buildCreateTransactionRequestBody() {
    // language=JSON
    return """
           {
          "terminalId": "e3211be6-d0cc-4718-905d-ab933cc91ecb",
          "terminalThreatScore": 50,
          "amount": {
            "currency": "DKK",
            "value": 50
          },
          "cardNumber": "4100000099998888"
        }""";
  }

  private static String buildCreateTransactionResponseBody() {
    // language=JSON
    return """
        {"status":"success","message":"Transaction created","fraudScore":25}""";
  }

  @Test
  void shouldCreateTransaction() {
    RestAssured.given()
        .header(HttpHeaders.CONTENT_TYPE, ContentType.JSON)
        .body(buildCreateTransactionRequestBody())
        .post("api/v1/transactions")
        .then()
        .statusCode(HttpURLConnection.HTTP_CREATED)
        .contentType(ContentType.JSON)
        .body(is(buildCreateTransactionResponseBody()));
  }

}
