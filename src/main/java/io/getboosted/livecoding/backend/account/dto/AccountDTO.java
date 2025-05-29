package io.getboosted.livecoding.backend.account.dto;

import io.getboosted.livecoding.backend.account.model.AccountType;

public class AccountDTO {
    private Integer id;
    private String name;
    private AccountType type;

    public AccountDTO() {
    }

    public AccountDTO(Integer id, String name, AccountType type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }
}
