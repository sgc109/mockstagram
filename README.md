# mockstagram

Microservices architecture-based Instagram clone web service project

## Target Architecture
(To Be Updated)

## How does it work?
* 각 마이크로서비스는 gRPC 기반의 API 를 제공하며, 대부분 Kotlin + Spring WebFlux 를 기반으로 개발되었다.
* Web frontend 는 React 로, Web BFF 는 Node.js(Express)로 개발되었으며, 모두 Typescript 기반이다.
* admin 도 frontend 는 React, backend 는 Node.js(Express)로 개발되었으며, 모두 Typescript 기반이다.
* DB 는 테스트 편의상 하나의 물리 DB 내 각 서비스별로 별도의 논리 DB 를 사용하며, 자신의 논리 DB 만 접근 가능하도록 권한을 초기화하도록 되어있다.

## Directory Structure

각 디렉터리는 api, batch, consumer, domain 의 서브 디렉터리를 포함한다(경우에 따라 일부는 없을 수도 있음).

* account
  * 계정, 사용자 관련 기능을 담당하는 서비스
* admin
  * 백오피스 서비스(backend & frontend)
* chat
  * 채팅 서비스
* comment
  * 댓글을 담당하는 서비스
* content
  * 콘텐츠 업로드 및 조회를 담당하는 서비스
* media-server
  * 이미지 업로드를 담당하는 서비스
* reaction
  * 유저 인터랙션(좋아요, 스크랩)을 담당하는 서비스
* notification
  * 인앱 알림을 위한 서비스
* push
  * 푸시 발송 및 예약을 위한 서비스
* recommend
  * 추천 피드 등의 API 를 위한 서비스
* search
  * 검색을 담당하는 서비스
* video-server
  * 비디오 업로드 및 인코딩을 담당하는 서비스
* web
  * React 기반의 웹 클라이언트와 웹 BFF

## How to run

* Run `./run-all.sh` script to run all services with docker compose.
