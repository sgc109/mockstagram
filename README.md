# mockstagram

Instagram clone web service project built on a microservices architecture.

## Target Architecture
![Architecture](https://github.com/user-attachments/assets/e1e82eaf-41f9-49b7-9bff-51e3355f5a14)

## Assumptions
* Given that Instagram is a global service used by users worldwide, each type of database has a **primary DB in one region, with replica DBs in several other regions**. Servers handling user requests are assumed to be geographically distant from the primary DB. To simulate this, I plan to introduce **intentional network latency and design and implement the system with these considerations in mind**.
* For testing convenience, the databases used by each microservice were configured as **separate logical DBs within a single physical DB**. However, each service was assigned a unique account, **granting access permissions exclusively to its own logical DB**.
* In the case of the BFF (Backend For Frontend), I adopted TypeScript and Node.js, the same tech stack as the web client, to maximize code reusability and enhance development and maintenance efficiency, in line with the purpose of its name.

## Directory Structure

The directories are organized by domain, and each microservice’s directory may include subdirectories named api, batch, consumer, and domain (with exceptions for admin, web, and media-server).

* `/admin`
  * Back-office service (backend & frontend)
* `/chat`
  * Chat service
* `/comment`
  * Service responsible for managing comments
* `/content`
  * Service handling content uploads and retrieval
* `/media-server`
  * Service for image and video uploads, encoding, and related tasks
* `/notification`
  * Service for in-app notifications
* `/push`
  * Service for push notifications and scheduling
* `/reaction`
  * Service managing user interactions (e.g., likes, saves)
* `/recommend`
  * Service providing APIs for recommendation feeds
* `/search`
  * Service responsible for search functionality
* `/user`
  * Service managing accounts and user-related features
* `/web`
  * React-based web client and web BFF

## How to run

* Run `./run-all.sh` script to run all services with docker compose.
