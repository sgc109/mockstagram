# mockstagram

Mock Instagram project for self-study and research purposes.

MSA 구조로 되어있어, 각 서비스가 별도의 디렉터리로 분리 되어있음.

## 동작 방식
* 각 마이크로서비스는 gRPC 기반의 API 를 제공하며, 대부분 Kotlin + Spring WebFlux 를 기반으로 개발되었다.
* Web frontend 는 React 로, Web BFF 는 Node.js(Express)로 개발되었으며, 모두 Typescript 기반이다.
* admin 도 frontend 는 React, backend 는 Node.js(Express)로 개발되었으며, 모두 Typescript 기반이다.
* DB 는 테스트 편의상 하나의 물리 DB 내 각 서비스별로 별도의 논리 DB 를 사용하며, 자신의 논리 DB 만 접근 가능하도록 권한을 초기화하도록 되어있다.

## 디렉터리 구조

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
* image-server
  * 이미지 업로드를 담당하는 서비스
* interaction
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

## (Todo)실행 방법

* docker-compose 를 통해 동시에 모든 서비스를 시작시킨다.
* 스크립트를 실행하여 fake user 들이 Web BFF 의 API 를 마구잡이로 call 하는 트래픽을 만들어낸다.