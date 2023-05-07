# data-warehouse
Java program to accept a bulk of deal details and persist them into DB.


## Introduction

The project is a web application for dealing currency exchange. Users can request a currency exchange deal and the system will save the deal to the database.

## Components

The project has the following components:

- `DealRequestDto`: A DTO (data transfer object) class for dealing requests. It contains the deal ID, from currency ISO code, to currency ISO code, and deal amount.
- `Deal`: An entity class representing a deal in the database. It contains the deal ID, from currency ISO code, to currency ISO code, deal date/time, and deal amount.
- `DealController`: A Spring Boot controller for dealing requests. It handles POST requests to save deals to the database.
- `DealService`: A service class for dealing requests. It contains business logic for saving deals to the database.
- `DealAlreadyExistsException`: An exception class for dealing requests. It is thrown when a deal with the same ID already exists in the database.
- `DealExceptionHandler`: A Spring MVC exception handler for dealing exceptions. It handles `DealAlreadyExistsException` and validation exceptions.

## Usage

To use the project, follow these steps:

1. Send a POST request to `/deals` with the following JSON payload:

```json
[
    {
        "dealId": "DEAL001",
        "fromCurrencyISOCode": "USD",
        "toCurrencyISOCode": "EUR",
        "dealAmount": 100.00
    },
    {
        "dealId": "DEAL002",
        "fromCurrencyISOCode": "EUR",
        "toCurrencyISOCode": "JPY",
        "dealAmount": 500.00
    }
]
```

The system will save the deals to the database and return a 201 Created status code.

If a deal with the same ID already exists in the database, the system will return a 400 Bad Request status code and an error message.

If the request payload is invalid (e.g. missing fields), the system will return a 400 Bad Request status code and a validation error message.