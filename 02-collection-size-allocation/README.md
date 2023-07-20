# Prerequisites

Follow the instructions from [Setup](../SETUP.md).

# Collection size allocation
In this demo we’ll show the impact of initializing a list with an initial size allocated.

# Conclusions
When adding a considerable amount of data, the impact in memory is much higher if the list is not created with an estimated size to pre-allocate. If you can estimate how many elements are you going to add into that collection, it’s a good practice to specify this number in the constructor. 
