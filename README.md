# creditcardapplication


The project runs in two profiles
1. Default - runs on http port 8080(unsecure)
2. Prod- runs on https port 8081

By default, prod profile is set as active. Prod profile enables spring security, which enables mutual authentication using x509 certificates.
All apis are accessible using http(s) on port 8081. To access api from browser or postman, you need to install client certificates. Client certificates are also checked in to this repository, please find nt-gateway in the root folder.

In order to run default profile, please use below VM arguments while running application
-Dspring.profiles.active=default

Below are the urls to access rest apis
Add card api -   https://localhost:8081/v1/accounts/creditcards/cards
Get all api-     https://localhost:8081/v1/accounts/creditcards

Sample request for Add card- consume application/json

{
    "cardHolderName":"riyadh",
    "cardNumber":"8763",
    "cardLimit":1000
}


