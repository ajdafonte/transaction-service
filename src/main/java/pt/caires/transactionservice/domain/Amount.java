package pt.caires.transactionservice.domain;

import java.util.Currency;
import java.util.Objects;

public record Amount(long value, Currency currency) {

  public boolean isHigherThan(Amount amount) {
    verifySameCurrency(amount);
    return this.value() > amount.value();
  }

  private void verifySameCurrency(Amount amount) {
    if (!Objects.equals(amount.currency(), this.currency())) {
      throw new IllegalArgumentException(
          String.format("Supplied Amount's currency %s is different from this Amount's currency %s",
              amount.currency(), this.currency()));
    }
  }

}
