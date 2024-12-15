package pt.caires.transactionservice.api.dto;

public record CreateTransactionResponseV1DTO(
    String status,
    String message,
    int fraudScore
) {
}
