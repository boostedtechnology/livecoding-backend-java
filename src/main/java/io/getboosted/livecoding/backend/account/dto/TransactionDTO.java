package io.getboosted.livecoding.backend.account.dto;

import java.time.Instant;
import java.util.List;

public class TransactionDTO {
    private Integer id;
    private Instant at;
    private String description;
    private List<TransactionEntryDTO> entries;

    public TransactionDTO() {
    }

    public TransactionDTO(Integer id, Instant at, String description, List<TransactionEntryDTO> entries) {
        this.id = id;
        this.at = at;
        this.description = description;
        this.entries = entries;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getAt() {
        return at;
    }

    public void setAt(Instant at) {
        this.at = at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TransactionEntryDTO> getEntries() {
        return entries;
    }

    public void setEntries(List<TransactionEntryDTO> entries) {
        this.entries = entries;
    }
}