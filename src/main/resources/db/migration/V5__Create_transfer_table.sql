CREATE TABLE IF NOT EXISTS transfer
(
    id_transfer       VARCHAR(255) PRIMARY KEY,
    reason            VARCHAR(255) NOT NULL,
    id_transfer_group VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_transfer_group) REFERENCES transfer_group (id_transfer_group)
);
