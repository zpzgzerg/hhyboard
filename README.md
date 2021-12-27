# Hhy board (호호유 보드)
- springboot
- spring security
- jpa
- docker (nginx, mysql)
- h2

## 실행 방법
### 로컬
- h2 데이터베이스 깔아야 함 (https://www.h2database.com/html/main.html) 
- h2 데이터베이스는 1.4.200 버전을 까는게 좋음 (spring boot의 의존성 버전이랑 호환이 맞는게 좋음)
- 처음 실행시 jdbcUrl을 jdbc:h2:~/hhy 로 설정하여 데이터베이스를 만들어줘야함 (파일 db)
- 그 다음 접속부터 jdbc:h2:tcp://localhost/~/hhy 로 접속하여 실행
- 실행은 간단함 
``` java 
HhyboardApplication 실행 (프로필을 기본이 local 임) 
```
- http://localhost:8080 접속 끝

### 로컬 도커
- 도커 for Windows or 도커 for Mac 깔려져 있어야 함
- https://www.docker.com/get-started
- 실행 방법
```
docker-compose -f docker-compose-dev.yml build 
docker-compose -f docker-compose-dev.yml up
```
- http://localhost 접속 (nginx가 프록시 처리를 하고 있어 8080 안붙여도 됨)

### 리얼
- **travis ci** (https://www.travis-ci.com/) 를 사용하여 깃헙과 연결해야 함
- 깃헙에 커밋하면 travis에서 빌드하고 aws s3 (아마존 파일 저장소) 로 파일 전송
- travis 빌드 과정 중에 도커 허브 (https://hub.docker.com/) 에 도커 빌드 한 파일을 push 함
- 도커 허브에 push 하므로 본인의 도커 허브에 아이디와 비번을 알맞게 설정해야함
- 아마존 서비스중 Elastic Beantalk 를 이용하는데 도커 관련해서 편하게 배포 할 수 있는 서비스임
- s3로 파일이 전송되면 Elastic Beantalk에서 해당 파일을 가져와서 도커 서비스를 올림
- 아마존 웹서비스는 비용이 과금되므로 조심 (처음 1년은 무료 - 프리티어 이용시...)
- 리얼에 관한 설정은 추후에 블로그에 게시할 예정
