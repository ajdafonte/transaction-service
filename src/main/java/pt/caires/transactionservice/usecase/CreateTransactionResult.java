package pt.caires.transactionservice.usecase;

public record CreateTransactionResult(String status, String message, int fraudScore) {
}
