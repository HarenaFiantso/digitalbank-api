CREATE OR REPLACE FUNCTION generate_account_statement(p_account_id VARCHAR, p_start_date DATE, p_end_date DATE)
RETURNS TABLE (
    transaction_date DATE,
    reference VARCHAR,
    reason VARCHAR,
    credit NUMERIC,
    debit NUMERIC,
    balance NUMERIC
) AS $$
BEGIN
RETURN QUERY
SELECT
    transaction_datetime::DATE AS transaction_date,
        id_transaction AS reference,
    transaction.reason,
    CASE WHEN transaction_type = 'INCOME' THEN amount ELSE 0 END AS credit,
    CASE WHEN transaction_type = 'EXPENSE' THEN amount ELSE 0 END AS debit,
    SUM(CASE WHEN transaction_type = 'INCOME' THEN amount ELSE -amount END) OVER (ORDER BY transaction_datetime) AS balance
FROM
    transaction
WHERE
        account_id = p_account_id AND
    transaction_datetime::DATE BETWEEN p_start_date AND p_end_date
ORDER BY
    transaction_datetime DESC;
END; $$
LANGUAGE plpgsql;