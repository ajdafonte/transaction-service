package pt.caires.transactionservice.api.dto;

public record CreateTransactionRequestV1DTO(
    String terminalId,
    int terminalThreatScore,
    AmountDTO amount,
    String cardNumber
) {
}
