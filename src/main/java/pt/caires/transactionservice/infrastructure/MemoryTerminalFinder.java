package pt.caires.transactionservice.infrastructure;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pt.caires.transactionservice.domain.terminal.Terminal;
import pt.caires.transactionservice.domain.terminal.TerminalFinder;

@Component
public class MemoryTerminalFinder implements TerminalFinder {

  private static final Logger LOGGER = LoggerFactory.getLogger(MemoryTerminalFinder.class);

  private static final Set<String> AVAILABLE_TERMINALS = new HashSet<>() {{
    add("e3211be6-d0cc-4718-905d-ab933cc91ecb");
  }};

  @Override
  public Optional<Terminal> findBy(String id, int threatScore) {
    LOGGER.info("Attempt to retrieve terminal {}", id);

    return AVAILABLE_TERMINALS.contains(id) ?
        Optional.of(new Terminal(id, threatScore)) :
        Optional.empty();
  }

}
