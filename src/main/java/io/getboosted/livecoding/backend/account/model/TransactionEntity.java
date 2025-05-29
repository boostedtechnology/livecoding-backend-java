package io.getboosted.livecoding.backend.account.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long at;
    private String description;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransactionEntryEntity> entries = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getAt() {
        return at;
    }

    public void setAt(Long at) {
        this.at = at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TransactionEntryEntity> getEntries() {
        return entries;
    }

    public void setEntries(List<TransactionEntryEntity> entries) {
        this.entries = entries;
    }

    public void addEntry(TransactionEntryEntity entry) {
        entries.add(entry);
        entry.setTransaction(this);
    }
}
