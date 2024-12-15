package pt.caires.transactionservice.domain;

import java.util.Currency;

public record Amount(long value, Currency currency) {
}
