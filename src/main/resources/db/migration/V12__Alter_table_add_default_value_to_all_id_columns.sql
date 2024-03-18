ALTER TABLE account ALTER COLUMN id_account SET DEFAULT gen_random_uuid();
ALTER TABLE balance ALTER COLUMN id_balance SET DEFAULT gen_random_uuid();
ALTER TABLE transfer_category ALTER COLUMN id_transfer_category SET DEFAULT gen_random_uuid();
ALTER TABLE transfer_group ALTER COLUMN id_transfer_group SET DEFAULT gen_random_uuid();
ALTER TABLE transfer ALTER COLUMN id_transfer SET DEFAULT gen_random_uuid();
ALTER TABLE transaction ALTER COLUMN id_transaction SET DEFAULT gen_random_uuid();
ALTER TABLE interest_rate ALTER COLUMN id_interest_rate SET DEFAULT gen_random_uuid();
ALTER TABLE debt ALTER COLUMN id_debt SET DEFAULT gen_random_uuid();