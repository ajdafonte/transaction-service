package pt.caires.transactionservice.domain.terminal;

import java.util.Optional;

public interface TerminalFinder {

  Optional<Terminal> findBy(String id, int threatScore);

}
