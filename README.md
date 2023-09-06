# APPZARA

<p>This is a microservice rest for ZARA recruitment.</p>

## About this project
* [About](#about)
* [Architecture](#architecture)
* [Techinologies](#techinologies)
* [How to run](#how-to-run)
* [Contributors](#contributors)

# About
This is a microservice REST created for ZARA recruitment.
The architecture is built hexagonal architecture, where there is the followings modules:

# Architecture
![Architecture-hexagonal.png](data%2FArchitecture-hexagonal.png)

#### API-REST
Adapter Layer, responsible for communicating with external calls e.g. REST.

#### APPLICATION
Layer that implements UseCase and is responsible for communicate with others modules e.g. Infrastructure.

#### BOOTSTRAP
Instantiates adapters and domain services and starts the entire application.

#### INFRAESTRUCURE
Persistence Layer, responsible for communicate with database.

# Technologies
- Java 17
- Spring boot 3.1.3
- Spring Data
- H2 Memory
- Test Reporter

# How to Run
<p>
Import in your IDE and execute the main class AppZaraApplication localized on bootstrap module.
In the API-REST module there is a PriceController with a GET endpoint with the following parameters:</p>

- startDate: date must be on format yyyy-MM-dd-HH.mm.ss
- productId: id of product e.g. 35455
- brandId: id of brand e.g: 1 (ZARA)


<p>A example of request:</p>

```shell
curl --location 'http://localhost:8080/price/startDate/2020-06-14-10.00.00/productId/35455/brandId/1'
```
<p>And an example of response is:<p>

```json lines
[
    {
        "brandId": 1,
        "productId": "35455",
        "priority": 1,
        "rate": 2,
        "startDate": "2020-06-14T15:00:00",
        "endDate": "2020-06-14T18:30:00",
        "amount": 25.45
    },
    {
        "brandId": 1,
        "productId": "35455",
        "priority": 1,
        "rate": 3,
        "startDate": "2020-06-15T00:00:00",
        "endDate": "2020-06-15T11:00:00",
        "amount": 30.50
    },
    {
        "brandId": 1,
        "productId": "35455",
        "priority": 1,
        "rate": 4,
        "startDate": "2020-06-15T16:00:00",
        "endDate": "2020-12-31T23:59:59",
        "amount": 38.95
    }
]
```

## Contributors
[@LauroSilveira](https://github.com/LauroSilveira)
