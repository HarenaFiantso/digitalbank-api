CREATE TABLE IF NOT EXISTS debt
(
    id_debt         VARCHAR(255) PRIMARY KEY,
    amount           DOUBLE PRECISION NOT NULL,
    debit_datetime   TIMESTAMP        NOT NULL,
    id_account       VARCHAR(255)     NOT NULL,
    id_interest_rate VARCHAR(255)     NOT NULL,
    FOREIGN KEY (id_account) REFERENCES account (id_account),
    FOREIGN KEY (id_interest_rate) REFERENCES interest_rate (id_interest_rate)
);