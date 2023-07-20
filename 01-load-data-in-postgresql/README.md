# Loading data in tables

This demo is meant to compare the cost between:
* Loading data in a table without indexes and restrictions, and creating them after the data load.
* Loading data in a table with all the indexes and restrictions online.

## 1. Getting the csv files

This demo will be made with the file ```yellow_tripdata_2022-01.csv``` that can be generated following the instructions in the readme file of the [demo parquet-csv-7z-files](../parquet-csv-7z-files/README.md).

Copy and paste the file ```yellow_tripdata_2022-01.csv``` from the [folder csv-output of the demo parquet-csv-7z-files](../parquet-csv-7z-files/csv-output/) to the folder in this project.

## 2. Running the postgreSQL and pgadmin

```docker compose up -d```

## 3. Adding the server in pgadmin

Make sure the container with name adding-primary-key is exited with code (0) and the other two (postgresql-database and pgadmin4-postgresql) are running. In the csv-data folder you should find a file with this name yellow_tripdata_with_pk_2022-01.

* Go to: http://localhost:5050/browser/ (Be aware that it might take some time to get the admin site available).
* User: pgadmin4@pgadmin.org
* Password: admin

**Right** click on Servers > Register > Server. Fill the form:

* Name: postgresql-database
* Switch to connection tab
* Host name/address: postgresql-database
* Username: admin
* Password: admin
* Activate the switch "Save the password"
* Click on Save button at the bottom right

> Never use such credentials and use secrets in a real environment.

## 4. Loading the data

Expand servers > Databases > postgres.

Right click on Tables > click on "Query tool".

> It'll be practical if you open three Query tool dialogs.

If you ran an example before, to be as fair as possible, delete any table that might be created and occupying space. **It is recommended to run this drop commands before any test.**

* Copy and paste the content of the file ```./sql/01-drop-all-tables.sql``` into a query editor.
* Press F5 or execute button.

### Loading data in a table with indexes

You can use the same query editor tool or open a new one in a new tab.

Let's test the time that takes to load the data in a table with the indexes already created.

* Copy and paste the content of the file ```./sql/02-create-table-with-indexes.sql``` into a query editor.
* Press F5 or execute button.

It might take around 30 seconds.

### Loading data in a table and creating the indexes after data load

You can use the same query editor tool or open a new one in a new tab.

Let's test the time that takes to load the data in a table with the indexes already created.

* Copy and paste the content of the file ```./sql/03-create-table-and-indexes-after-load.sql``` into a query editor.
* Press F5 or execute button.

It might take around 13 seconds.

## Stopping and cleaning

 ```docker compose down```

# Conclusions

In this example we've seen that loading data in a table with indexes and constraints (internally, a primary key is a unique index) it's much slower than in a "flat" table.

If you are creating the table as a part of the process, consider to defer the index creation when the data is loaded. The process of creating an index from the scratch with all the data loaded is faster than maintaining the index in each insert. Also, the index might be more balanced and compacted.

If the table is already created, this feature is available in your database, and you don’t have any functional restriction, consider to disable the constraints and index maintenance (even dropping the indexes). Next load the data, and when the data is loaded, you can enable the constraints again and create the indexes and restrictions.

This is valid in SQL and NoSQL databases.
