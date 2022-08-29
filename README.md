# API Usage Tracker
A billing service to track usage of it’s APIs and prepare a monthly bill for customers.

## Running the project
Follow the steps below to run the
1) Execute mvn compile

2) Execute mvn exec:java -Dexec.mainClass="com.usage.tracker.app.UsageTrackApplication"

3) Run the following command to send a request to the non-secure endpoint:

        curl --location --request POST 'http://localhost:9001/api/v1/usage' \
        --header 'Content-Type: application/json' \
        --data-raw '{
        "username":"bigmish"
        }'
   If successful, you will receive an `HTTP 200 OK` response.