import sys
import os
import pandas as pd
import datetime as dt

PARQUET_FOLDER = '/app/parquet-input/'
CSV_FOLDER = '/app/csv-output/'

SEPARATOR = '|'
MAX_ROWS = 0

print('Process started')
sys.stdout.flush()
globalStart = dt.datetime.now()

for parquet_file in os.listdir(PARQUET_FOLDER):
    if parquet_file.endswith(".parquet"):
        start = dt.datetime.now()
        print('Starting to convert file: ' + parquet_file)
        sys.stdout.flush()
        csv_file_name = CSV_FOLDER + parquet_file.replace(".parquet", ".csv")

        df = pd.read_parquet(PARQUET_FOLDER + parquet_file)
        df.to_csv(csv_file_name, index=False, sep=SEPARATOR)

        end = dt.datetime.now()
        print('Finished conversion of file: ' + parquet_file)
        print('Took: ' + str(end - start))
        sys.stdout.flush()

print("Finished converting all files. Took: " + str(dt.datetime.now() - globalStart))
