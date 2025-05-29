# Live Coding Challenge (Java)

### System Prerequisites

* Java 17 or newer

### Setup

Run the service with the following command
```bash
   ./gradlew bootRun
``` 

Run all the test cases with the following command
```bash
   ./gradlew clean test
``` 

## Task 1: Implement `DELETE /accounts/{id}/` endpoint

We want to be able to delete an account by its ID. Add a new endpoint to the controller to delete an account by its ID.

- Uncomment the lines in `io.getboosted.livecoding.backend.account.controller.AccountControllerTest`
- Run `./gradlew test --tests '*AccountControllerTest' ` to test if it works.


To seed test data, you can use `curl` to call the POST endpoint. This has already been implemented for you.

```bash
curl -X POST -H "Content-Type: application/json" -d '{"name":"Cash","type":"ASSET"}' http://localhost:3000/accounts
```


## Task 2: Implement `POST /transactions/`

**Context**

A transaction in double-entry accounting is a record of a financial transaction. Each transaction has a description and a list of entries. Each entry has an account ID, a debit amount, and a credit amount. Within a transaction, the sum of the debit amounts must equal the sum of the credit amounts. There must be at least two entries in a transaction.

**Task**

Create the `POST /transactions/` endpoint.

The following is an example that shows a request body with three account entries on the transaction.

```json
{
  "at": "2024-11-11T00:00:12.000Z",
  "description": "Office supplies at Staples",
  "entries": [
    {
      "accountId": 1,
      "type": "DEBIT",
      "amount": 12456
    },
    {
      "accountId": 56,
      "type": "CREDIT",
      "amount": 10000
    },
    {
      "accountId": 1,
      "type": "CREDIT",
      "amount": 2456
    }
  ]
}
```

* Only worry about the POST endpoint for now. The other CRUD operations do not need to be implemented.

* The models are already created for Transaction and Transaction Entry, but feel free to modify / delete them as desired.

 - Uncomment the lines in `io.getboosted.livecoding.backend.account.controller.TransactionControllerTest` 
 - Run `./gradlew test --tests '*TransactionControllerTest' ` to test if it works.


## Task 3: Add a `balance` field to the response of `GET /accounts/{id}/`

**Context**

Each account has a "balance" based on the transactions involving the account.

* For Asset and Expense accounts, the balance is the sum of all the debit entries involving the account minus the sum of all the credit entries involving the account.
* For Liability, Equity, and Revenue accounts, the balance is the sum of all the credit entries involving the account minus the sum of all the debit entries involving the account.

Create your own test suite for this task.

**Task**

On the response of `GET /accounts/{id}`, add a `balance` field on the response.


## Task 4: Convert the `DELETE /accounts/{id}` endpoint to use a soft delete

Update the `DELETE /accounts/{id}` endpoint to prevent the deletion of accounts used in any transactions.

In short, this should not delete the record from the database, but instead put the record in a "deactivated" state. In this deleted state, you should still be able to see the
account in the `GET /accounts/{id}` endpoint, but it should not be included in the `GET /accounts` endpoint or be usable in the POST /transactions/ endpoint as an entry.

Create your own test suite for this task.

## Task 5: Implement `GET /accounts/{id}/ledger` endpoint
**Context**

In accounting systems, a ledger provides a chronological list of transactions affecting a specific account, showing how the balance evolved over time.

This endpoint will return a detailed transaction history of an account, with a running balance after each entry.
It must:

* Show all transactions involving the account.
* Include for each transaction:
  * Date
  * Description
  * All entries in the transaction
  * Debit/Credit applied to this account
  * Running balance after the transaction (using rules from Task 3)

**Task**

* Implement a new endpoint: `GET /accounts/{id}/ledger`
* For the specified account:
  * Fetch all transactions involving it (as either DEBIT or CREDIT entry).
  * Order them chronologically (ascending by timestamp).
  * For each transaction, compute how the account balance changed after the transaction.
* Return an array of ledger entries. Each entry must contain:
  * transactionId
  * date
  * description
  * entries[] (all entries in that transaction)
  * change: the net debit/credit for this account
  * runningBalance: the cumulative balance after this transaction

Sample Response
```json
[
  {
    "transactionId": 2,
    "date": "2024-05-01T12:00:00Z",
    "description": "Office supplies",
    "entries": [
      { "accountId": 1, "type": "DEBIT", "amount": 5000 },
      { "accountId": 2, "type": "CREDIT", "amount": 5000 }
    ],
    "change": 5000,
    "runningBalance": 5000
  },
  {
    "transactionId": 2,
    "date": "2024-05-05T09:30:00Z",
    "description": "Customer payment",
    "entries": [
      { "accountId": 1, "type": "CREDIT", "amount": 7000 },
      { "accountId": 3, "type": "DEBIT", "amount": 7000 }
    ],
    "change": -7000,
    "runningBalance": -2000
  }
]

```

## Bonus Task Add GET /accounts/{id}/ledger.csv to stream the ledger as CSV
**Task**

* Implement `GET /accounts/{id}/ledger.csv` to stream the ledger as CSV