# Rest API APPZARA

<p>This is a microservice rest for ZARA recruitment.</p>

## About this project
* [About](#about)
* [Architecture](#architecture)
* [Techinologies](#techinologies)
* [How to run](#how-to-run)
* [How to run tests](#how-to-run-tests)
* [Contributors](#contributors)

# About
**Business rules**
<p> Given a day and time, a database search is carried out to bring up the price with the tariff to be applied.
If more than one element is found, some rules will apply:</p>
<ul> 
    <li>If the result finds two rates that coincide, the one with the highest value will apply.</li> 
</ul>

# Architecture
![Architecture-hexagonal.png](data/Architecture-hexagonal.png)

#### APPLICATION
Layer that implements all use case and communicate with others modules e.g. Infrastructure.

#### BOOTSTRAP
This module is responsible for instantiating the other modules of the application.
As the name suggests, it is the bootstrap.

#### DOMAIN
This module contains the database entities.

#### INFRAESTRUCURE
This module connects with the outside world, such as databases, Http Rest calls, message brokers.

# Technologies
- Java 17
- Spring boot 3.1.3
- Spring Data
- H2 Memory
- Jacoco Reporter

# How to Run
<p>
Clone the repository:

```
git clone clone https://github.com/LauroSilveira/appzara.git
```

Get in the appzara folder:
``` 
cd appzara/appzara
```
To execute the Spring context put following command in your prompt:
```
mvn spring-boot:run
```

To stop 
```
CTRL C
```

In the Infraestructure module there is a PriceController with a GET endpoint with the following parameters:

- startDate (mandatory): date **must be int the format yyyy-MM-dd-HH.mm.ss** e.g. 2020-06-16-21.00.00
- productId (mandatory): id of product e.g. 35455
- brandId (mandatory): id of brand e.g: 1 (ZARA)
</p>

<p>Lets see some examples of request</p>
<p>Request to day 14 of 2020 at 10:00 hrs</p>

```
curl --location 'http://localhost:8080/price/startDate/2020-06-14-10.00.00/productId/35455/brandId/1'
```

<p>The response will be:</p>

```json lines
{
  "brandId": 1,
  "productId": "35455",
  "priority": 1,
  "rate": 4,
  "startDate": "2020-06-15T16:00:00",
  "endDate": "2020-12-31T23:59:59",
  "amount": 38.95
}
```

<p>Request to day 14 of 2020 at 16:00 hrs</p>

```
curl --location 'http://localhost:8080/price/startDate/2020-06-14-16.00.00/productId/35455/brandId/1'
```

<p>The response will be:</p>

```json lines
{
  "brandId": 1,
  "productId": "35455",
  "priority": 1,
  "rate": 4,
  "startDate": "2020-06-15T16:00:00",
  "endDate": "2020-12-31T23:59:59",
  "amount": 38.95
}
```

<p>Request to day 14 of 2020 at 21:00 hrs</p>

```
curl --location 'http://localhost:8080/price/startDate/2020-06-14-21.00.00/productId/35455/brandId/1'
```
<p>The response will be:</p>

```json lines
{
    "brandId": 1,
    "productId": "35455",
    "priority": 1,
    "rate": 4,
    "startDate": "2020-06-15T16:00:00",
    "endDate": "2020-12-31T23:59:59",
    "amount": 38.95
}
```

<p>Request to day 15 of 2020 at 21:00 hrs</p>

```
curl --location 'http://localhost:8080/price/startDate/2020-06-15-21.00.00/productId/35455/brandId/1'
```
<p>The response will be:</p>

```json lines
{
  "brandId": 1,
  "productId": "35455",
  "priority": 1,
  "rate": 3,
  "startDate": "2020-06-15T00:00:00",
  "endDate": "2020-06-15T11:00:00",
  "amount": 30.50
}
```
<p>Request to day 16 of 2020 at 21:00 hrs</p>

```
curl --location 'http://localhost:8080/price/startDate/2020-06-16-21.00.00/productId/35455/brandId/1'
```
<p>The response will be:</p>

```json lines
{
    "brandId": 1,
    "productId": "35455",
    "priority": 1,
    "rate": 3,
    "startDate": "2020-06-15T00:00:00",
    "endDate": "2020-06-15T11:00:00",
    "amount": 30.50
}
```

# How to run Tests

<p> This project has jacoco-report with we are able to see coverage after execute tests.
Once you are inside appzara (pom.xml parente) open your favorite prompt execute:</p>

```
mvn test
```

After execute tests jacoco-report will generate an index.html file with coverage.
The directory of this file is `appzara/bootstrap/target/site/jacoco-aggregate/index.html`.

![directory_index_jacoco.png](data/directory_index_jacoco.png)

<p>Open it in your default browser to see the coverage of each module</p>

![coverage-updated.png](data/coverage-updated.png)

## Contributors
[@LauroSilveira](https://github.com/LauroSilveira)
