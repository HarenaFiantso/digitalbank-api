CREATE TABLE IF NOT EXISTS transfer_group
(
    id_transfer_group    VARCHAR(255) PRIMARY KEY,
    registration_date    TIMESTAMP     NOT NULL,
    effective_date       TIMESTAMP     NOT NULL,
    id_transfer_category VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_transfer_category) REFERENCES transfer_category (id_transfer_category)
);