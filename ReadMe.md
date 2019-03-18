# Packer Application

In order to setup, maven4 and jdk8 are required.

How to run this application :

```sh
## build jar file and run unit and integration tests
mvn clean package

## run application
java -jar target/packer-0.0.1-SNAPSHOT.jar -file src/test/resources/item_list.txt

```

### References:
https://www.geeksforgeeks.org/unbounded-knapsack-repetition-items-allowed/

https://www.wikiwand.com/en/Knapsack_problem