# S1
스프링 시큐리티를 사용한 로그인 게시판 프로젝트

JAVA, Spring boot, JPA, thymeleaf, Mustache, AWS EC2 RDS, MariaDB

서비스 주소 : https://bit.ly/3rlvLqF

ver는 배포가 됐었던 커밋만 나열합니다.


# 책 부분 : ~ 24시간 배포 스크립트 완성 ver 1.0.0
@ branch 'main' commit '프로젝트 배포완료'


@ as is
- mustache, h2

ver 1.0.1
# 템플릿 엔진 mustache > thymeleaf ver 1.0.1
@ branch 'custom' commit '타임리프 수정1'

@ to be
- mustache 작성 내용 전부 as_is 폴더로 이동
- 작성된 3 페이지 thymeleaf로 변경
- https://fadet-coding.tistory.com/27

# 템플릿 엔진 mustache > thymeleaf ver 1.0.2
@ branch 'custom' commit '스크립트 수정2'

@to be
- index.html 디자인 수정
- 관련 링크 추가

# 템플릿 레이아웃 분할, 페이징 기능 추가 ver 1.0.3
@ branch 'custom' commit '~페이징 테스트코드'

@to be
- 공통 layout 생성
- 글목록 페이지 추가(/posts-list)
- css 파일 분리
- 페이징 기능 추가(기능 추가 및 테스트코드 작성)
- yml 설정으로 DB에 더미데이터 추가

# 검색 기능 추가, 페이징 + 검색 로직 변경 ver 1.0.4
@ branch 'custom' commit '중간 배포본 포트 수정6'

@to be
- 검색 기능 추가 및 페이징에 검색 적용
- 기존 로직을 도메인 모델에 가까운 로직으로 수정(컨트롤러, 서비스에선 최대한 객체의 메서드 순서만 보장하도록)
- 추가된 기능에 맞춰 html, js의 url 전부 수정

# 학습 사항 용 블로그 포스트, 깃헙 레포 링크 게시판 추가  ver 1.0.5
@ branch 'custom' commit '링크 임시 완성'

@to be
- 현재 필자의 학습 사항을 파악할 수 있는 게시판 추가(관련 링크)
- link의 추가로 domain, package 트리 일부 수정
