CREATE TABLE IF NOT EXISTS transaction
(
    id_transaction   VARCHAR(255) PRIMARY KEY,
    transaction_type VARCHAR(7)       NOT NULL,
    amount           DOUBLE PRECISION NOT NULL,
    id_account       VARCHAR(255)     NOT NULL,
    id_transfer      VARCHAR(255)     NOT NULL,
    FOREIGN KEY (id_account) REFERENCES account (id_account),
    FOREIGN KEY (id_transfer) REFERENCES transfer (id_transfer)
);
