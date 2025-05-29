CREATE TABLE accounts
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(100) NOT NULL
);

CREATE TABLE transactions
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    at          BIGINT       NOT NULL,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE transaction_entries
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    transaction_id INT         NOT NULL,
    account_id     INT         NOT NULL,
    amount         INT         NOT NULL,
    type           VARCHAR(50) NOT NULL,
    CONSTRAINT fk_transaction FOREIGN KEY (transaction_id) REFERENCES transactions (id) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT fk_account FOREIGN KEY (account_id) REFERENCES accounts (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);
