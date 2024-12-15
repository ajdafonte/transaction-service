package pt.caires.transactionservice.infrastructure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pt.caires.transactionservice.domain.transaction.Transaction;
import pt.caires.transactionservice.domain.transaction.TransactionRepository;

@Component
public class DummyTransactionRepository implements TransactionRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(DummyTransactionRepository.class);

  @Override
  public Transaction save(Transaction transaction) {
    LOGGER.debug("Saving transaction: {}", transaction);
    return transaction;
  }

}
