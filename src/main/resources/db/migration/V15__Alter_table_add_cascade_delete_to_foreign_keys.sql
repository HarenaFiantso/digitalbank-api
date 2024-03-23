ALTER TABLE balance
    DROP CONSTRAINT IF EXISTS balance_id_account_fkey,
    ADD CONSTRAINT balance_id_account_fkey FOREIGN KEY (id_account)
        REFERENCES account (id_account)
        ON DELETE CASCADE;

ALTER TABLE transaction
    DROP CONSTRAINT IF EXISTS transaction_id_account_fkey,
    ADD CONSTRAINT transaction_id_account_fkey FOREIGN KEY (id_account)
        REFERENCES account (id_account)
        ON DELETE CASCADE;

ALTER TABLE debt
    DROP CONSTRAINT IF EXISTS debt_id_account_fkey, -- Drop the existing foreign key constraint if it exists
    ADD CONSTRAINT debt_id_account_fkey FOREIGN KEY (id_account)
        REFERENCES account (id_account)
        ON DELETE CASCADE;