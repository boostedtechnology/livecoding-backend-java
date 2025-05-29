package io.getboosted.livecoding.backend.account.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.List;

public class CreateTransactionDTO {
    @NotNull(message = "Transaction timestamp is required")
    private Instant at;

    @NotBlank(message = "Transaction description is required")
    private String description;

    @NotEmpty(message = "Transaction must have at least one entry")
    @Size(min = 2, message = "Transaction must have at least two entries")
    @Valid
    private List<CreateTransactionEntryDTO> entries;

    public CreateTransactionDTO() {
    }

    public CreateTransactionDTO(Instant at, String description, List<CreateTransactionEntryDTO> entries) {
        this.at = at;
        this.description = description;
        this.entries = entries;
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

    public List<CreateTransactionEntryDTO> getEntries() {
        return entries;
    }

    public void setEntries(List<CreateTransactionEntryDTO> entries) {
        this.entries = entries;
    }
}