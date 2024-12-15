package pt.caires.transactionservice.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Terminal {

  private final String id;
  private final int threatScore;

  public Terminal(String id, int threatScore) {
    this.id = id;
    this.threatScore = threatScore;
  }

}
