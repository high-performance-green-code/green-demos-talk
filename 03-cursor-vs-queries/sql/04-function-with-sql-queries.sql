CREATE OR REPLACE FUNCTION update_is_cheap_with_sql_queries() RETURNS BOOLEAN AS $$
BEGIN
    UPDATE base_table_yellow_trip_data
    SET is_cheap = 'N'
    WHERE total_amount >= 15;

    UPDATE base_table_yellow_trip_data
    SET is_cheap = 'Y'
    WHERE total_amount < 15;

    RETURN TRUE;
END;
$$ LANGUAGE plpgsql;