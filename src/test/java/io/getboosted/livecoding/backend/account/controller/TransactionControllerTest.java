/*
 *
 TODO: Uncomment these lines and run the tests after completing task 2
 */
package io.getboosted.livecoding.backend.account.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.getboosted.livecoding.backend.account.dto.CreateTransactionDTO;
import io.getboosted.livecoding.backend.account.dto.CreateTransactionEntryDTO;
import io.getboosted.livecoding.backend.account.model.TransactionEntryType;
import io.getboosted.livecoding.backend.account.repository.AccountRepository;
//import io.getboosted.livecoding.backend.account.repository.TransactionRepository;
//import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Arrays;

//import static org.hamcrest.Matchers.hasSize;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
@TestPropertySource("classpath:application.properties")
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

//    @Autowired
//    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    private CreateTransactionDTO createTransactionDTO;

    @BeforeEach
    public void setup() {
        CreateTransactionEntryDTO debitEntry = new CreateTransactionEntryDTO(1, TransactionEntryType.DEBIT, 10000);
        CreateTransactionEntryDTO creditEntry = new CreateTransactionEntryDTO(2, TransactionEntryType.CREDIT, 10000);
        
        createTransactionDTO = new CreateTransactionDTO(
                Instant.now(),
                "Test Transaction",
                Arrays.asList(debitEntry, creditEntry)
        );
    }
/*
 *
    @AfterEach
    public void cleanup() {
        transactionRepository.deleteAll();
    }

    @Test
    public void testCreateTransaction() throws Exception {
        long countBefore = transactionRepository.count();

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createTransactionDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description").value("Test Transaction"))
                .andExpect(jsonPath("$.entries", hasSize(2)))
                .andExpect(jsonPath("$.entries[0].type").value("DEBIT"))
                .andExpect(jsonPath("$.entries[0].amount").value(10000))
                .andExpect(jsonPath("$.entries[1].type").value("CREDIT"))
                .andExpect(jsonPath("$.entries[1].amount").value(10000));

        long countAfter = transactionRepository.count();
        assertEquals(countBefore + 1, countAfter);
    }

    @Test
    public void testCreateTransactionUnbalanced() throws Exception {
        CreateTransactionEntryDTO debitEntry = new CreateTransactionEntryDTO(1, TransactionEntryType.DEBIT, 10000);
        CreateTransactionEntryDTO creditEntry = new CreateTransactionEntryDTO(2, TransactionEntryType.CREDIT, 5000);
        
        CreateTransactionDTO unbalancedDTO = new CreateTransactionDTO(
                Instant.now(),
                "Unbalanced Transaction",
                Arrays.asList(debitEntry, creditEntry)
        );

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(unbalancedDTO)))
                .andExpect(status().isBadRequest());

        assertEquals(0, transactionRepository.count());
    }

    @Test
    public void testCreateTransactionWithInvalidAccount() throws Exception {
        CreateTransactionEntryDTO debitEntry = new CreateTransactionEntryDTO(999, TransactionEntryType.DEBIT, 10000);
        CreateTransactionEntryDTO creditEntry = new CreateTransactionEntryDTO(2, TransactionEntryType.CREDIT, 10000);
        
        CreateTransactionDTO invalidAccountDTO = new CreateTransactionDTO(
                Instant.now(),
                "Invalid Account Transaction",
                Arrays.asList(debitEntry, creditEntry)
        );

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidAccountDTO)))
                .andExpect(status().isNotFound());

        assertEquals(0, transactionRepository.count());
    }

    @Test
    public void testCreateTransactionWithMultipleEntries() throws Exception {
        CreateTransactionEntryDTO debitEntry1 = new CreateTransactionEntryDTO(1, TransactionEntryType.DEBIT, 12456);
        CreateTransactionEntryDTO creditEntry1 = new CreateTransactionEntryDTO(2, TransactionEntryType.CREDIT, 10000);
        CreateTransactionEntryDTO creditEntry2 = new CreateTransactionEntryDTO(1, TransactionEntryType.CREDIT, 2456);
        
        CreateTransactionDTO multiEntryDTO = new CreateTransactionDTO(
                Instant.now(),
                "Multi-Entry Transaction",
                Arrays.asList(debitEntry1, creditEntry1, creditEntry2)
        );

        mockMvc.perform(post("/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(multiEntryDTO)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.description").value("Multi-Entry Transaction"))
                .andExpect(jsonPath("$.entries", hasSize(3)));

        assertEquals(1, transactionRepository.count());
    }

 */
}