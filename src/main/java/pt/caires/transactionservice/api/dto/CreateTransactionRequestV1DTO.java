package pt.caires.transactionservice.api.dto;

public record CreateTransactionRequestV1DTO(
    String terminalId,
    int terminalThreadScore,
    AmountDTO amountDTO,
    String cardNumber
) {
}
