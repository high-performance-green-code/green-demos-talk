# Prerequisites

Follow the instructions from [Setup](../SETUP.md).

# Using Hibernate

This demo compares the performance inserting multiple rows in a table using:

* Hibernate in a single transaction or commit.
* Hibernate in a commit or transaction per each row.
* HQL in a single transaction or commit.
* Hibernate using session.
* Hibernate using session in batches
* And a SQL command using JDBC driver

# Conclusions

We can observe that using the commit in each row has a negative impact in performance. It’s important to place the commit at the right level.

Using batches it’s a good benefit in terms of performance and memory.

The fastest approach and the most efficient is to use SQL through the JDBC driver interacting more directly with the database.
