# N26 Backend Challenge
API to record and retrieve real-time statistics abot transactions in a given timeframe.

# How to run
1.  Install [Maven](https://maven.apache.org/download.cgi) locally
2.  Run: `mvn spring-boot:run`

Alternatively, you can use the included Maven wrapper and run `./mvnw spring-boot:run` instead.

# Adding transactions 
Run the following in your terminal:
```
curl -d '{"amount":1, "timestamp":1478192204000}' -H "Content-Type: application/json" -X POST -i localhost:8080/transactions
```
Make sure you change the timestamp number to the current Unix epoch timestamp.

# Getting statistics 
The following will give you statistics about all transactions in the last 60 seconds:
```
curl -i localhost:8080/statistics
```
