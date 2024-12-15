package pt.caires.transactionservice.usecase;

import pt.caires.transactionservice.domain.Amount;
import pt.caires.transactionservice.domain.Terminal;

public record CreateTransactionRequest(
    Terminal terminal,
    Amount amount,
    String cardNumber
) {}