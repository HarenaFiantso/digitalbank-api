CREATE TYPE transaction_type AS ENUM ('EXPENSE', 'INCOME');

CREATE CAST ( varchar AS transaction_type ) WITH INOUT AS IMPLICIT;