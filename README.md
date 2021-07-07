# Spot Dash

---

## Introduction

This project gets the daily Spotify's trending 50 tracks playlist and checks the average of energy and valence of the given songs. It is based in a Service Oriented Architecture (SOA), by dividing the system into two services:

1. spot-dash-producer: interacts with Spotify Web API by getting daily trending 50 tracks, then produces and sends a Kafka message under "track" topic;
2. spot-dash-consumer: receives/consumes "track" topic. After getting daily 50 tracks, then this services gets the average of valence and average of energy, storing the songs and the analysis on MongoDB. 

The producer service has an scheduled method responsible to make this interaction along Spotify API and kafka message's production. Also, one can test this service manually, by using:

`curl -i -X POST -H "Content-Type: application/json" -H "Accept: */*" POST http://localhost:9001/v1/spot-dash/get-track-infos`

The consumer service has two useful endpoints to check the analysis:

1. To get averages from a given day: `curl -i -X GET -H "Content-Type: application/json" -H "Accept: */*"  http://localhost:9000/v1/spot-dash/averages?analysisDate=<the day of your choice, in the format: YYYY-MM-DD>
`
2. All available averages: `curl -i -X GET -H "Content-Type: application/json" -H "Accept: */*"  http://localhost:9000/v1/spot-dash/averages/all`

---

## Languages and Tools used

This project uses the following languages and tools:

1. Java: one of the most popular OO programming languages;
2. Spring Boot: Java-based framework that makes initial configuration easier, focusing on business logic;
3. spring-boot-spotify-api: a useful framework to be used along with Spring Boot;
4. Kafka: distributed event streaming platform;
5. MongoDB: NoSQL database.

---

## Prerequisites

The following tools must be installed:

* OpenJDK 11
* MongoDB
* Apache Kafka

For Spotify API usage, it is necessary to have an application in [Spotify for Developers](https://developer.spotify.com/dashboard/login) in order to have a Client ID and a Client Secret. After obtaining those, one must configure the environment variables SPOTIFY_USER=<your Spotify's client id> and SPOTIFY_PASSWORD=<your Spotify's client secret>.

---

## How to run

For spot-dash-producer, go to gcloud03/spot-dash-producer and run `./mvnw spring-boot:run`. Similarly, for spot-dash-consumer, go to gcloud03/spot-dash-consumer and run the same command. Then it is possible to make the tests with cURL.