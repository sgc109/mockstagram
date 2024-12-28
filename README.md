# mockstagram

Instagram clone web service project built on a microservices architecture.

## Target Architecture
![Architecture](https://github.com/user-attachments/assets/beef331c-372d-4233-a4b6-d72446b1f72e)

## How to run

* Run `./start.sh` script to run all services with docker compose.
  * It will ask permission once to add domains(`mockstagram.com` and `image.mockstagramcdn.com`) on `/etc/hosts` file.
* Open Browser and type `mockstagram.com` on address bar. Then, the site will open.

![image](https://github.com/user-attachments/assets/69b9f333-71ba-4550-9315-1d1b812d7111)

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
  * Service managing user reactions (e.g., likes, saves)
* `/recommend`
  * Service providing APIs for recommendation feeds
* `/search`
  * Service responsible for search functionality
* `/user`
  * Service managing accounts and user-related features
* `/web`
  * React-based web client and web BFF

## Assumptions
* Given that Instagram is a global service used by users worldwide, each type of database has a **primary DB in one region, with replica DBs in several other regions**. Servers handling user requests are assumed to be geographically distant from the primary DB. To simulate this, I plan to introduce **intentional network latency and design and implement the system with these considerations in mind**.
 
## QnA
* Why aren't all the microservices based on Spring Boot and Gradle managed within the same gradle project?
  * According to Conway’s Law, the structure of a system tends to mirror the structure of the organization and the services used here will be managed by multiple teams in the real world. If a mono-repo is not being used, each service will be located in a separate remote repository managed by its respective team. Some teams may manage multiple services within a single repository within a same gradle project. This can vary depending on the organizational structure and ownership, so I'd rather assume that each service is stored in a separate remote repository.
* Why is BFF (Backend For Frontend) developed using Typescript and Node.js?
  * The concept of BFF (Backend For Frontend) originally emerged to be managed by the team developing and operating a specific client application, which is why it uses the same technology stack as the web client.
* Why do all the microservices share a single database even though it's a microservices architecture?
  * For the sake of simplicity and convenience in development and testing, the databases used by each microservice were configured as **separate logical DBs within a single physical DB**. However, each service was assigned a unique account, **granting access permissions exclusively to its own logical DB**.
