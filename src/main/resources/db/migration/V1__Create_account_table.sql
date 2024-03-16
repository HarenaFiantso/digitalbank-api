CREATE TABLE IF NOT EXISTS account
(
    id_account     VARCHAR(255) PRIMARY KEY,
    first_name     VARCHAR(255) NOT NULL,
    last_name      VARCHAR(255) NOT NULL,
    birth_date     DATE NOT NULL,
    monthly_salary DOUBLE PRECISION NOT NULL,
    over_drafted   BOOLEAN NOT NULL
);