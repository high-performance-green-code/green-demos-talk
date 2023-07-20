CREATE TABLE base_table_yellow_trip_data (
    id int,
    VendorID int,
    tpep_pickup_datetime TIMESTAMP,
    tpep_dropoff_datetime TIMESTAMP,
    passenger_count float,
    trip_distance float,
    RatecodeID float,
    store_and_fwd_flag varchar(50),
    PULocationID int,
    DOLocationID int,
    payment_type int,
    fare_amount float,
    extra float,
    mta_tax float,
    tip_amount float,
    tolls_amount float,
    improvement_surcharge float,
    total_amount float,
    congestion_surcharge float,
    airport_fee float
);

COPY base_table_yellow_trip_data FROM '/var/lib/postgresql/csv-data/yellow_tripdata_with_pk_2022-01.csv' DELIMITER '|' CSV HEADER;

CREATE INDEX idx_passenger_count_1 ON base_table_yellow_trip_data(passenger_count);
CREATE INDEX idx_trip_distance_1 ON base_table_yellow_trip_data(trip_distance);
CREATE INDEX idx_total_amount_1 ON base_table_yellow_trip_data(total_amount);
ALTER TABLE base_table_yellow_trip_data ADD CONSTRAINT pk_base_table_yellow_trip_data PRIMARY KEY (id);
