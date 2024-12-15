package pt.caires.transactionservice.usecase;

import pt.caires.transactionservice.domain.Amount;

public record CreateTransactionRequest(
    String terminalId,
    int terminalThreatScore,
    Amount amount,
    String cardNumber
) {}