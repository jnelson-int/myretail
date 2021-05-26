# myretail

My Retail is a small reactive web service that combines data from product naming data services with product pricing information stored locally in a mongoDB nosql datastore. For ease of demonstration, an embedded mongoDB datastore is included in the dependencies and autoconfigures itself on app startup.

## gradle wrapper
This project is best built and run using gradle wrapper.

## running locally
Navigate to the project's root directory and execute the following in a terminal

`./gradlew bootrun`

## endpoints
There are 2 endpoints available at `localhost:8080`. Both endpoints have a response payload like
```
{
    "id": 13860428,
    "name": "The Big Lebowski (Blu-ray)",
    "current_price": {
        "value": 124.45,
        "currency_code": "USD"
    }
}
```
- `GET /products/{id}` will return product name and pricing details by product.id
- `PUT /products/{id}` with a payload like below will allow updating pricing of the product identified
```
{
    "current_price": {
        "value": 84.20
    }
}
```
