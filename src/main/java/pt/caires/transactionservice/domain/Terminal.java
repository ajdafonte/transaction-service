package pt.caires.transactionservice.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Terminal {

  private final String id;
  private final int fraudScore;

  public Terminal(String id, int fraudScore) {
    this.id = id;
    this.fraudScore = fraudScore;
  }

}
