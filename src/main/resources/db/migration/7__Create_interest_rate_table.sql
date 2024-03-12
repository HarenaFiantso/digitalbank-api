CREATE TABLE IF NOT EXISTS interest_rate
(
    id_interest_rate       VARCHAR(255) PRIMARY KEY,
    value                  DOUBLE PRECISION NOT NULL,
    interest_rate_datetime TIMESTAMP        NOT NULL
);