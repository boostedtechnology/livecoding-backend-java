package io.getboosted.livecoding.backend.account.dto;

import io.getboosted.livecoding.backend.account.model.TransactionEntryType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CreateTransactionEntryDTO {
    @NotNull(message = "Account ID is required")
    private Integer accountId;

    @NotNull(message = "Entry type is required")
    private TransactionEntryType type;

    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Amount must be greater than zero")
    private Integer amount;

    public CreateTransactionEntryDTO() {
    }

    public CreateTransactionEntryDTO(Integer accountId, TransactionEntryType type, Integer amount) {
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public TransactionEntryType getType() {
        return type;
    }

    public void setType(TransactionEntryType type) {
        this.type = type;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}