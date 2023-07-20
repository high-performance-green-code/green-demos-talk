# Cursor VS SQL queries

In this demo we'll modify some data using a cursor, and next we'll do the same modification using SQL queries to check which method is more efficient.

## Running the example

Follow the steps detailed in the [demo load-data-in-postgresql](../01-load-data-in-postgresql/README.md).

Open a new Query Tool as is detailed there.

Make sure that the table base_table_yellow_trip_data is created and the data loaded. If not, run the script ```sql/create-table-and-indexes-after-load.sql``` available in the previous demo in the same Query Tool. Once you have the table created you can clear the content.

Alter the table to add an additional column by following the next steps:

* Clear the content of the Query Tool or open a new tab.
* Copy and paste the code in the file ```01-alter-table.sql``` into the Query Tool.
* Press F5 to create the function.

## Setting the value of the column using a cursor

Say that we want to mark as cheap all the rides cheaper or equal than $15 setting a 'Y' in the column is_cheap. Higher prices will we marked as 'N' in the same column. The column that contains the amount is total_amount.

Let's update the data through a cursor.

* Clear the content of the Query Tool or open a new tab.
* Copy and paste the code in the file ```02-function-with-cursor.sql``` into the Query Tool.
* Press F5 to create the function.
* In the same tab or a new one copy and paste the file ```03-call-function-with-cursor.sql```
* Press F5 to create the function.

It might take around 50 seconds.

## Setting the value of the column using sql sentences

Let's update the data through update sentences.

* Clear the content of the Query Tool or open a new tab.
* Copy and paste the code in the file ```04-function-with-sql-queries.sql``` into the Query Tool.
* Press F5 to create the function.
* In the same tab or a new one copy and paste the file ```05-call-function-with-queries.sql```

It might take around 32 seconds.

# Conclusions

Cursor is clearly the slowest option as normally queries run faster than explicit cursors. Depending on the complexity of the queries, the execution plan might be useful to fine tune the queries.

This example also applies to many database management systems. 
