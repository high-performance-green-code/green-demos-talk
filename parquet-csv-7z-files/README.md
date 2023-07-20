# .parquet, .csv and .7z file sizes

## 1. Getting the parquet files

Download the parquet files and place them into the folder parquet-input:
* [File 1 yellow_tripdata_2022-01.parquet](https://d37ci6vzurychx.cloudfront.net/trip-data/yellow_tripdata_2022-01.parquet)
* [File 2 green_tripdata_2022-01.parquet](https://d37ci6vzurychx.cloudfront.net/trip-data/green_tripdata_2022-01.parquet)
* [File 3 fhv_tripdata_2022-01.parquet](https://d37ci6vzurychx.cloudfront.net/trip-data/fhv_tripdata_2022-01.parquet)

If you encounter any issue with any of the links above, you can try to find them again in [its original site](https://www.nyc.gov/site/tlc/about/tlc-trip-record-data.page)

Place the files in the folder parquet-input

## 2. Converting the parquet files to csv

Once we've got the files in parquet format through the network, we can convert them to .csv format and load its data locally into a database that might need this format.

We'll create a Docker image to convert files with the code in python to convert the files and pyarrow pandas libraries installed. The code is in the file parquet-to-csv.py and the image will be created based on the file Dockerfile.

The container will iterate all the parquet files in the input directory and convert them in csv in the output directory. The more files you have there, the longer time it'll take.

Just run:

```docker compose up -d```

And wait the container to finish.

The process converting all the files detailed may take around a minute and the container will stop when it is done.

>If you want to remove the container, run:
>
>```docker compose down```

## 3. Compress the files in .7z from your OS

Compress .csv and the .parquet files into .7z format using the appropriate command in your OS

# Size comparison

> Sizes might be slightly different in Linux and Windows

|File|csv|.7z from .csv|.parquet|.7z from .parquet|
|---|---|---|---|---|
fhv_tripdata_2022-01|70,8 MB|8,4 MB|11,7 MB|11,7 MB|
green_tripdata_2022-01|6,6 MB|0,8359 MB|1,3 MB|1,2 MB|
yellow_tripdata_2022-01|257,6 MB|31,1 MB|38,1 MB|38,3 MB|

# Conclusions

The smallest size is .7z from .csv, but it's a file format that doesn't allow to work directly with it. Even that it is not the smallest, .parquet file is fairly small (almost like .csv from .7z) but this format allows to work with its data directly without any compression or decompression operation. Depending on your needs accessing the data and the overhead compressing and decompressing the data it's a good choice.

The benefit compressing a .parquet file to a .7z file is pretty limited. The tradeoff with the additional processing to compress the data might be not worth, and you may consider to compress it from a csv file which is the smallest size.

Always will depend on each concrete scenario.