package pt.caires.transactionservice.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Transaction {

  private static final int FRAUD_SCORE_SUCCESS_TRANSACTION = 0;
  private static final int FRAUD_SCORE_HIGH_AMOUNT_FOR_THREAT_SCORE = 25;

  private final String id;
  private final String terminalId;
  private final Amount amount;
  private final String cardNumber;
  private final String status;
  private final String message;
  private final int fraudScore;

  public Transaction(String id,
      String terminalId,
      Amount amount,
      String cardNumber,
      String status,
      String message,
      int fraudScore) {
    this.id = id;
    this.terminalId = terminalId;
    this.amount = amount;
    this.cardNumber = cardNumber;
    this.status = status;
    this.message = message;
    this.fraudScore = fraudScore;
  }

  static Transaction createSuccess(String id,
      String terminalId,
      Amount amount,
      String cardNumber) {
    return new Transaction(id,
        terminalId,
        amount,
        cardNumber,
        "success",
        "Transaction created",
        FRAUD_SCORE_SUCCESS_TRANSACTION);
  }

  static Transaction createFailure(String id,
      String terminalId,
      Amount amount,
      String cardNumber) {
    return new Transaction(id,
        terminalId,
        amount,
        cardNumber,
        "failure",
        "Amount too large for terminal threat score",
        FRAUD_SCORE_HIGH_AMOUNT_FOR_THREAT_SCORE);
  }

}
