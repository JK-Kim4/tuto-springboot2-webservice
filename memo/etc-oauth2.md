- 스프링부트로 웹 서비스 츨시하기 Tutorial
  ========================================

> 이동욱 개발자의 '스프링부트로 웹 서비스 출시하기'를 참고하여
> 스프링부트 / JPA / AWS 관련 기술 학습을 목표로 한다.

### oauth2 관련 자료 정리

---

- Google cloud platform에 애플리케이션 등록 작업이 사전 수행되어야 한다.
  - 리디렉션 URL : 인증 성공 후 구글에서 리다이렉트 할 URL을 설정하는 부분이다.  
    Spring-security에서는 기본적으로 {도메인}/login/oauth2/code/{소셜서비스코드}로 URL 리다이렉션을 지원한다.
  
- @Enumerated(EnumType.String)
  - JPA로 데이터베이스에 저장할 경우 해당 Enum값을 어떤 형태로 저장할 지 결정한다.
  - Default로 int형이 설정되어있어 String으로 변경

- spring-boot-starter-oauth2-client
  - spring boot starter로 제공되는 oauth2 의존 ( 소셜 기능 구현 시 필요 )

- @EnableWebSecurity
  - Spring Security 설정 활성화

- csrf().disable().headers().frameOptions().disable();
  - H2-console을 사용하기 위해 csrf와 frameOption에 대한 거부 처리를 설정을 비활성화 한다.
  - 보안 취약점을 위해 실제 상용 애플리케이션 배포 시에는 제거할 필요가 있음.

- oauth2Login
  - OAuth 2 로그인 기능에 대한 설정 시작점

- userInfoEndpoint
  - OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당
  - userService 인터페이스를 구현체로 등록하여 리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행할 기능을 명시해줌 ( customOAuth2UserService )

- registrationId
  - 현재 로그인 진행중인 서비스를 구분하는 코드

- userNameAttributeName
  - OAuth2 로그인 진행 시 key가 되는 필드

- OAuthAttributes
  - OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담은 클래스

- of()
  - OAuth2User객체를 통해 반환되는 사용자 정보는 map객체이기 때문에 값을 하나하나 변환할 필요가 있다.

- toEntity()
  - User entity를 생성