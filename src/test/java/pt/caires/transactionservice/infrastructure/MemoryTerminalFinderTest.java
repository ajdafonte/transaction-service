package pt.caires.transactionservice.infrastructure;

import static org.assertj.core.api.BDDAssertions.then;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pt.caires.transactionservice.domain.Terminal;

class MemoryTerminalFinderTest {

  private MemoryTerminalFinder finder;

  @BeforeEach
  void setUp() {
    this.finder = new MemoryTerminalFinder();
  }

  @Test
  void shouldFindTerminal() {
    var expected = new Terminal("e3211be6-d0cc-4718-905d-ab933cc91ecb", 50);

    var result = finder.findBy("e3211be6-d0cc-4718-905d-ab933cc91ecb", 50);

    then(result)
        .isPresent()
        .hasValue(expected);
  }

  @Test
  void shouldNotFindTerminal() {
    var result = finder.findBy("id", 50);

    then(result).isNotPresent();
  }

}