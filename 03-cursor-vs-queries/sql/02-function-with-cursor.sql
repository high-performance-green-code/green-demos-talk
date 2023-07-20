CREATE OR REPLACE FUNCTION update_is_cheap_with_cursor() RETURNS BOOLEAN AS $$
DECLARE
    r RECORD;
BEGIN
    FOR r IN SELECT total_amount, id FROM base_table_yellow_trip_data LOOP
        IF r.total_amount >= 15 THEN
            UPDATE base_table_yellow_trip_data
            SET is_cheap = 'N'
            WHERE id = r.id;
        ELSE
            UPDATE base_table_yellow_trip_data
            SET is_cheap = 'Y'
            WHERE id = r.id;
        END IF;
    END LOOP;
   
    RETURN TRUE;
END;
$$ LANGUAGE plpgsql;
