# Hhy board (호호유 보드)
- springboot
- springsecurity
- jpa
- docker

## 실행 방법
### 로컬
- 가장 간단함.
``` java 
HhyboardApplication 실행
```
- 단, h2 데이터베이스 깔아야 함
- https://www.h2database.com/html/main.html

### 로컬 도커
- 도커 for Windows or 도커 for Mac 깔려져 있어야 함
- https://www.docker.com/get-started
- gradle clean 후 bootjar 실행
- docker-compose build
- docker-compose up
- http://localhost 접속

### 리얼
- travis 사용하여 깃헙에 커밋하면 빌드 후 아마존 s3로 파일 전송
- 아마존 웹서비스 엘라스틱빈토크 사용하여 도커 설정
- 도커 허브에 push 하므로 본인의 도커 허브에 아이디와 비번 설정해야함
- 아마존 웹서비스는 비용이 과금되므로 조심 (처음 1년은 무료)