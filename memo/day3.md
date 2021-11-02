- 스프링부트로 웹 서비스 츨시하기 Tutorial
  ========================================

> 이동욱 개발자의 '스프링부트로 웹 서비스 출시하기'를 참고하여
> 스프링부트 / JAP / AWS 관련 기술 학습을 목표로 한다.

### day 3 - springBoot & jpa 로 간단한 api 생성

---

 - domain.posts.Posts.class 
   - @Entity : JPA annotaion으로 테이블과 링크될 클래스임을 명신한다.
   - @Id : 테이블의 PK 필드를 명시한다.
   - @GeneratedValue : PK생성 규칙을 명시한다.
   - @Setter annotation을 선언하지 않은 이유 : 해당 클래스의 인스턴스 값의 초기화 및 
     변경 지점을 명확하게 하기 위해 Setter를 선언하지 않는다. 여기서는 @Builder 를 사용하여 객체 선언 시 해당 값들을 지정하게 된다.
   - 
 - ? : import static org.assertj.core.api.Assertions.assertThat;
   - assertThat 메소드 참조하는 라이브러리
----
   
 - 기존 Web Layer ->  Controller -> Service ( 비지니스 로직 ) -> Dao   방식으로
 - 처리되던 트랜잭션 스크립트 방식을 사용하지 않고, domain class에서 비지니스 로직을 처리하도록 한다.