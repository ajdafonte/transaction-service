package pt.caires.transactionservice.domain;

import static org.assertj.core.api.BDDAssertions.then;
import java.util.Currency;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

class AmountTest {

  private static final Currency CURRENCY_DK = Currency.getInstance("DKK");
  private static final Amount AMOUNT = new Amount(50, CURRENCY_DK);

  @Test
  void shouldBeHigherAmount() {
    var amount = new Amount(500, CURRENCY_DK);

    var result = amount.isHigherThan(AMOUNT);

    then(result).isTrue();
  }

  @Test
  void shouldNotBeHigherAmount() {
    var amount = new Amount(5, CURRENCY_DK);

    var result = amount.isHigherThan(AMOUNT);

    then(result).isFalse();
  }

  @Test
  void shouldFailToCompareHigherAmountsWhenDifferentCurrency() {
    var amount = new Amount(50, Currency.getInstance("EUR"));

    var throwable = BDDAssertions.catchThrowable(() -> amount.isHigherThan(AMOUNT));

    then(throwable)
        .isNotNull()
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Supplied Amount's currency DKK is different from this Amount's currency EUR");
  }

}