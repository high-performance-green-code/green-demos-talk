# Prerequisites

Follow the instructions from [Setup](../SETUP.md).

# Regex expressions

This demo shows the difference in performance validating expressions using regex and code. Not all expressions can be easily translated in code, but some not so complex expressions are much faster and safer if are validated programmatically.

The tests are a common expression to check if a string contains valid values for language and region. The checks are done in three ways:

* Validating with the expression
* Precompiling the expression
* Using code

# Conclusions

The code validation is faster and more optimal in a way that sometimes does not appear in the stats of the memory and CPU consumption.

Precompiled expression is a bit faster than not precompiled, but no too much benefit.

Before running any validation is worth and safer to check the length of the string independently of the method used. **Always check the length of a string before.**

A coded validation is wide more optimal in CPU, memory consumption and execution time than a regular expression.

Depending on the complexity of the resulting code, it is recommended to implement this solution instead of a validation using a regular expression.
