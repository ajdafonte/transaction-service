package pt.caires.transactionservice.domain;

import java.util.Optional;

public interface TerminalFinder {

  Optional<Terminal> findBy(String id, int threatScore);

}
