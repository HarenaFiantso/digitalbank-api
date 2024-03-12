CREATE TABLE IF NOT EXISTS balance
(
    id_balance       VARCHAR(255) PRIMARY KEY,
    amount           DOUBLE PRECISION NOT NULL,
    balance_datetime TIMESTAMP NOT NULL,
    id_account       VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_account) REFERENCES account (id_account)
);