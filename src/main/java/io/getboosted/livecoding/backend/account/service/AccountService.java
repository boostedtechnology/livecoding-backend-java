package io.getboosted.livecoding.backend.account.service;

import io.getboosted.livecoding.backend.account.dto.AccountDTO;
import io.getboosted.livecoding.backend.account.dto.CreateAccountDTO;
import io.getboosted.livecoding.backend.account.model.AccountEntity;
import io.getboosted.livecoding.backend.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public AccountDTO createAccount(CreateAccountDTO createAccountDTO) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setName(createAccountDTO.getName());
        accountEntity.setType(createAccountDTO.getType());

        AccountEntity savedEntity = accountRepository.save(accountEntity);
        return convertToDTO(savedEntity);
    }

    private AccountDTO convertToDTO(AccountEntity entity) {
        return new AccountDTO(entity.getId(), entity.getName(), entity.getType());
    }
}
