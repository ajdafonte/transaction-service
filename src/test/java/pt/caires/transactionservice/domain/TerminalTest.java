package pt.caires.transactionservice.domain;

import static org.assertj.core.api.BDDAssertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TerminalTest {

  private static final String ID = "e3211be6-d0cc-4718-905d-ab933cc91ecb";

  @ParameterizedTest
  @ValueSource(ints = {0, 50, 100})
  void shouldCreateTerminal(int threatScore) {
    var result = new Terminal(ID, threatScore);

    then(result)
        .isNotNull()
        .isInstanceOf(Terminal.class);
  }

  @ParameterizedTest
  @ValueSource(ints = {-1, 101})
  void shouldFailToCreateTerminalWhenThreatScoreNotBetweenZeroAndOneHundred(int threatScore) {
    var throwable = catchThrowable(() -> new Terminal(ID, threatScore));

    then(throwable)
        .isNotNull()
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage(
            String.format("Invalid threatScore: %d. ThreatScore must be between 0 and 100",
                threatScore));
  }

}