# S1 개요
스프링 시큐리티를 사용한 로그인 게시판 프로젝트
JAVA, Spring boot, JPA, thymeleaf, Mustache, AWS EC2/RDS, MariaDB

- 문제 발생 : 스프링 부트의 기초 학습을 진행했지만 예제가 아닌 실제 mvc가 작동하는 페이지를 배포해보고 싶어짐 
- 해결 : 이동욱님의 저서 '스프링부트와 AWS로 혼자 구현하는 웹서비스'를 참고하여 실제 웹서비스를 AWS로 배포



url 링크: https://bit.ly/3rlvLqF (EC2 프리티어 만료로 임시 페쇄 / 현재 사용불가)
  
post 링크: https://fadet-coding.tistory.com/10



# 사용 기술

- Spring : Spring Boot로 서블릿 기반 Spring MVC 서버 구현, Spring Security의 Oauth 2.0으로 소셜 로그인 구현
- JPA : Spring data jpa + JPQL로 entity를 영속화하여 DB와 연결
- AWS : EC2 서버 내에서 무중단 스크립트 배포
- JavaScript : 비동기 HTTP 통신을 위해 ajax 사용
- thymeleaf : 타임리프를 사용해 검색, 페이징 구현

# ver 1.0.0
@branch 'main' commit '프로젝트 배포완료'

@책 부분 : ~ 24시간 배포 스크립트 완성

# ver 1.0.1
@branch 'custom' commit '타임리프 수정1'

@as is
- mustache
- h2

@to be
- mustache 작성 내용 전부 as_is 폴더로 이동
- 작성된 3 페이지 thymeleaf로 변경
- https://fadet-coding.tistory.com/27

# ver 1.0.2
@branch 'custom' commit '스크립트 수정2'

@to be
- index.html 디자인 수정
- 관련 링크 추가

# ver 1.0.3
@branch 'custom' commit '~페이징 테스트코드'

@to be
- 템플릿 레이아웃 분할, 페이징 기능 추가 
- 공통 layout 생성
- 글목록 페이지 추가(/posts-list)
- css 파일 분리
- 페이징 기능 추가(기능 추가 및 테스트코드 작성)
- yml 설정으로 DB에 더미데이터 추가

# ver 1.0.4
@branch 'custom' commit '중간 배포본 포트 수정6'

@to be
- 검색 기능 추가 및 페이징에 검색 적용
- 기존 로직을 도메인 모델에 가까운 로직으로 수정(컨트롤러, 서비스에선 최대한 객체의 메서드 순서만 보장하도록)
- 추가된 기능에 맞춰 html, js의 url 전부 수정

# ver 1.0.5
@branch 'custom' commit '링크 임시 완성'

@to be
- 현재 fadet이 진행한 학습 사항을 파악할 수 있는 게시판 추가(관련 링크)
- link의 추가로 domain, package 트리 일부 수정
