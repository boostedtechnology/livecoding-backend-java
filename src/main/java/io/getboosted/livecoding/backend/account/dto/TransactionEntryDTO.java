package io.getboosted.livecoding.backend.account.dto;

import io.getboosted.livecoding.backend.account.model.TransactionEntryType;

public class TransactionEntryDTO {
    private Integer id;
    private Integer accountId;
    private String accountName;
    private TransactionEntryType type;
    private Integer amount;

    public TransactionEntryDTO() {
    }

    public TransactionEntryDTO(Integer id, Integer accountId, String accountName, TransactionEntryType type, Integer amount) {
        this.id = id;
        this.accountId = accountId;
        this.accountName = accountName;
        this.type = type;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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