package pt.caires.transactionservice.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Terminal {

  private final String id;
  private final int threatScore;

  public Terminal(String id, int threatScore) {
    validateThreatScore(threatScore);
    this.id = id;
    this.threatScore = threatScore;
  }

  private static void validateThreatScore(int threatScore) {
    if (threatScore < 0 || threatScore > 100) {
      throw new IllegalArgumentException(
          String.format("Invalid threatScore: %d. ThreatScore must be between 0 and 100",
              threatScore));
    }
  }

}
