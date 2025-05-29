package io.getboosted.livecoding.backend.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.getboosted.livecoding.backend.account.dto.AccountDTO;
import io.getboosted.livecoding.backend.account.dto.CreateAccountDTO;
import io.getboosted.livecoding.backend.account.model.AccountEntity;
import io.getboosted.livecoding.backend.account.model.AccountType;
import io.getboosted.livecoding.backend.account.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@TestPropertySource("classpath:application.properties")
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AccountRepository accountRepository;

    private CreateAccountDTO createAccountDTO;

    @BeforeEach
    public void setup() {
        createAccountDTO = new CreateAccountDTO("New Account", AccountType.INCOME);
    }

    @AfterEach
    public void cleanup() {
        accountRepository.deleteAll();
    }

    @Test
    public void testGetAllAccounts() throws Exception {
        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Account"))
                .andExpect(jsonPath("$[0].type").value("ASSET"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Test Account 2"))
                .andExpect(jsonPath("$[1].type").value("LIABILITY"));
    }

    @Test
    public void testGetAllAccountsEmpty() throws Exception {
        accountRepository.deleteAll();

        mockMvc.perform(get("/accounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void testCreateAccount() throws Exception {
        long countBefore = accountRepository.count();

        MvcResult result = mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createAccountDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("New Account"))
                .andExpect(jsonPath("$.type").value("INCOME"))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertNotNull(content);

        AccountDTO responseDTO = objectMapper.readValue(content, AccountDTO.class);

        assertNotNull(responseDTO.getId());
        assertEquals("New Account", responseDTO.getName());
        assertEquals(AccountType.INCOME, responseDTO.getType());

        long countAfter = accountRepository.count();
        assertEquals(countBefore + 1, countAfter);

        AccountEntity savedAccount = accountRepository.findById(responseDTO.getId()).orElse(null);
        assertNotNull(savedAccount);
        assertEquals("New Account", savedAccount.getName());
        assertEquals(AccountType.INCOME, savedAccount.getType());
    }

    @Test
    public void testCreateAccountValidationFailure() throws Exception {
        CreateAccountDTO invalidDTO = new CreateAccountDTO();

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDTO)))
                .andExpect(status().isBadRequest());

        assertEquals(2, accountRepository.count());
    }

/*
 *
    // Task 1

    @Test
    public void testDeleteAccount() throws Exception {
        long countBefore = accountRepository.count();

        MvcResult result = mockMvc.perform(delete("/accounts/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("New Account"))
                .andExpect(jsonPath("$.type").value("INCOME"))
                .andReturn();

        String content = result.getResponse().getContentAsString();
        assertNotNull(content);

        AccountDTO responseDTO = objectMapper.readValue(content, AccountDTO.class);

        assertNotNull(responseDTO.getId());
        assertEquals("New Account", responseDTO.getName());
        assertEquals(AccountType.INCOME, responseDTO.getType());

        long countAfter = accountRepository.count();
        assertEquals(countBefore - 1, countAfter);

        AccountEntity deletedAccount = accountRepository.findById(responseDTO.getId()).orElse(null);
        assertNull(deletedAccount);
    }

    @Test
    public void testDeleteNonExistAccount() throws Exception {
        MvcResult result = mockMvc.perform(delete("/accounts/{id}", 500))
                .andExpect(status().isNotFound())
                .andReturn();
    }

 */
}
