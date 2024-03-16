CREATE TABLE IF NOT EXISTS transfer_group
(
    id_transfer_group    VARCHAR(255) PRIMARY KEY,
    registration_date    DATETIME     NOT NULL,
    effective_date       DATETIME     NOT NULL,
    id_transfer_category VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_transfer_category) REFERENCES transfer_category (id_transfer_category)
);