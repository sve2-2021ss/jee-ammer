# Ratings Admin Microservice

First exercise for the subject 20_SVE2UE at FH OÖ Campus Hagenberg. The exercise is based on Quarkus and extends a custom version of the Istio sample application [bookinfo](https://github.com/istio/istio/tree/master/samples/bookinfo).
> See: [MC523-bookinfo](https://github.com/ammerzon/MC523-bookinfo)

## Preface

* application: always refers to bookinfo which consists of multiple microservice
* microservice: always refers to a single entity of the bookinfo application

## 🚩 Goal
The goal for this project is a simple microservice which offers a REST API to maintain ratings in a database. Additionally, the microservice has the following requirements:

* use Kotlin as programming language
* use Panache to write entities more efficient
* support for OpenTracing, Metrics and Health
* use of JPA validation for passed entities
* serve an OpenAPI endpoint
* automatically generate Kubernetes manifests

The bookinfo application itself is broken into four separate microservices as the following images displays:

* **productpage**: The ``productpage`` microservice calls the ``details`` and ``reviews`` microservices to populate the page.
* **details**: The ``details`` microservice contains book information.
* **reviews**: The ``reviews`` microservice contains book reviews. It also calls the ``ratings`` microservice.
* **ratings**: The ``ratings`` microservice contains book ranking information that accompanies a book review.
* **ratings-admin**: The ``ratings-admin`` microservice offers an REST API to maintain the ratings.

![](.github/architecture.png)

## ✨ Functionality

The microservice has the functionality to add and maintain the ratings of a product. Therefore, it offers the following endpoints:

| Methods | Routes           | Description                    |
|---------|------------------|--------------------------------|
| GET     | `/ratings`       | Get all ratings                |
| GET     | `/ratings/{id}`  | Get rating by id               |
| GET     | `/ratings/avg`   | Get the average of all ratings |
| GET     | `/ratings/count` | Get the amount of ratings      |
| POST    | `/ratings`       | Create a new rating            |
| PUT     | `/ratings/{id}`  | Update a rating                |
| DELETE  | `/ratings/{id}`  | Delete a rating                |

## 🏗 Architecture 

The architecture of the microservice can be seen in the following image:

![](.github/cld.png)

## 🚀 Get started

You can follow the guide in the repository of the [bookinfo](https://github.com/ammerzon/MC523-bookinfo) application to deploy a native image in a local Kubernetes cluster.

Another option is the following command which requires the correct credentials to your MongoDb in the `application.properties` file:
```shell
./gradlew quarkusDev
```

## Features of Quarkus

### Kubernetes manifests

### OpenTracing, metrics and health

### Kotlin support

