- 스프링부트로 웹 서비스 츨시하기 Tutorial
  ========================================

> 이동욱 개발자의 '스프링부트로 웹 서비스 출시하기'를 참고하여
> 스프링부트 / JPA / AWS 관련 기술 학습을 목표로 한다.

### day 4-2 : JPA를 사용한 CRUD 구현 정리

---

1. Entity / VO / DTO
 - Entity : RDS의 사용에 있어 Database와 직접적으로 관계를 갖는 객체로 DB table의 컬럼과 Entity의 필드는 일치해야한다.
 - VO :  값을 표현하기 위한 객체로 Database의 테이블 컬럼 외 추가적인 필드를 가질 수 있다.
 - DTO : 데이터 전송을 위한 객체로 API에서 데이터를 반환하거나 읽기 위해 사용된다.
- 기존 spring을 활용하여 프로젝트를 진행할 때에는 VO class 와 DTO 클래스의 구분을 하지 않고 사용해왔다. 이것은 내부 속성의 값이 동일하여
어떠한 객체를 사용하여도 데이터베이스를 다룰 수 있기 때문이었다. 하지만 이러한 문제는 유지/보수 시 큰 어려움을 야기한다.1
해당 프로젝트에서는 VO 객체 대신 Entity 객체를 사용하여 두 객체 간 역할을 명확하게 구분하려한다.1

2. Business logic
 - 기존 프로젝트에서는 Service Layer에서 비지니스 로직이 수행되었다.
 (예를 들어 User 객체의 정보 변경 요청이 온다면,
 Web Layer : DTO로 Request 객체 전달
 Controller : 전달된 객체를 Service Layer로 전달
 Service Layer : User 객체 update에 해당하는 비지니스 로직 수행)
 하지만 Service Layer를 비지니스 로직 수행 역할로만 사용하게 된다면, 해당 계층의 볼륨이 커지며 유지 / 보수가 힘들어 질 뿐만 아니라
 Service Layer에서 가장 중요하게 생각해야되는 Transaction 처리 및 도메인간 순서 보장의 역할이 소홀해지게 된다. ( Service Layer는 오직 비지니스 수행 영역으로만 사용하게 된)
 때문에 비지니스 로직은 Domain class 내에서 수행하도록 하며, Service Layer는 Transaction과 도메인 순서 보장 역할일 수행하게 만들어
 서비스 효율 및 안정성 영역에 효과를 볼 수 있도록 한다.

---

 - Annotation
    - JPA
        - @Entity : JPA에서 관리하는 Entity 객체로 지정, 클래스의 카멜케이스로 작성된 필드명을 언더스코어로 변환하여 테이블 매칭
        - @Id : 맵핑될 테이블의 PK필드임을 명시
        - @GeneratedValue : PK생성 규칙을 명시 ( @Id 와 함께 사용 )
        - @Column : 테이블의 컬럼을 나타냄, 명시하지 않더라도 자동으로 등록 (기본값에 변경이 필요한 경우 명시하여 사용)
    - Lombok
        - @NoArgsConstructor : 기본 생성자 자동 생성
        - @Getter : Getter 메소드 자동 생성
        - @Builder : 해당 클래스의 빌드 패턴 등록, builder 패턴으로 객체 생성 시 key, value 확인 용이

    ---

    - JPA에서 사용되는 Entity 객체는 Setter 메소드가 없기 때문에 setKey()를 사용할 수 없다. 때문에 객체의 생성은 생성자를 통해
DB와 연동될 객체를 생성 한다. 이 때 값의 변화가 필요하다면, 목적에 맞는 메소드를 작성하여 사용한다.
해당 프로젝트에서는 Builder 패턴을 사용하여 객체를 생성하도록 한다.

    ---

- @Autowired와 생성자
    - 이전 spring 프로젝트에서는 의존 주입을 위해 @Autowired 어노테이션을 사용하였다. ( 객체 타입과 일치하는 객체를 찾아 자동으로 주입 )
    이 방법 외에도 setter를 사용하는 방법과 생성자를 사용하여 의존 주입을 이루는 방법이 존재한다.
    이 중 생성자를 사용하는 방식이 권장되는 방식이며, 해당 프로젝트에서는 생성자 방식을 사용하여 의존주입 하기로 한다.
    프로젝트에서 service class를 확인해 보면 final로 선언된 필드와 @RequiredArgsConstructor 어노테이션을 확인 할 수 있다. 해당 어노테이션은
    final 필든 @Notnull이 붙은 필드를 인자로 받는 생성자를 자동으로 생성하게 된다.1
    ```
    ## 사용 예시 (출처 : https://webdevtechblog.com/)
    @Service
    @RequiredArgsConstructor
    public class RequiredArgsConstructorDIServiceExample {
      private final FirstRepository firstRepository;
      private final SecondRepository secondRepository;
      private final ThirdRepository thirdRepository;

      // ...
    }
    ```

    ```
    ## 위와 같이 선언을 한다면 컴파일 과정에서서
    @Service
    public class RequiredArgsConstructorDIServiceExample {
      @ConstructorProperties({"firstRepository", "secondRepository", "thirdRepository"})
      public RequiredArgsConstructorDIServiceExample(FirstRepository firstRepository, SecondRepository secondRepository, ThirdRepository thirdRepository) {
        this.firstRepository = firstRepository;
        this.secondRepository = secondRepository;
        this.thirdRepository = thirdRepository;
      }
    }
    ##이와 같이 생성자가 자동으로 생성되는 것을 확인할 수 있다.
   ```






