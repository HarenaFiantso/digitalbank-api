ALTER TABLE "transfer_group"
    DROP COLUMN id_transfer_category CASCADE;

DROP TABLE "transfer_category";

CREATE TABLE IF NOT EXISTS "transaction_category"
(
    id_transaction_category VARCHAR(255) PRIMARY KEY DEFAULT gen_random_uuid(),
    name                 VARCHAR(255) NOT NULL,
    description          TEXT         NOT NULL
);

ALTER TABLE "transaction"
    ADD COLUMN transaction_datetime
        TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT (now() at time zone 'utc'),
    ADD COLUMN reason
        VARCHAR(255),
    ADD COLUMN id_transaction_category
        VARCHAR(255) REFERENCES "transaction_category" (id_transaction_category)
;
