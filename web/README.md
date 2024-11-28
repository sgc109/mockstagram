
# 디렉터리 구조
* bff
  * Node.js(Express) 기반 backend 디렉터리
* frontend
  * React 기반 frontend 디렉터리
* shared
  * bff(backend)와 frontend 가 공유하는 interface 들을 모아 놓은 디렉터리
  * API 요청, 응답, 모델 등

# 실행방법
* 터미널을 2개 띄워놓고 한 쪽은 bff, 다른 한 쪽은 frontend 에 위치하고 각각 `npm start` 를 실행
* TODO - bff 쪽에 nodemon 적용하여 코드 저장할 때마다 재실행되도록 하기