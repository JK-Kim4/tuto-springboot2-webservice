- 스프링부트로 웹 서비스 츨시하기 Tutorial
  ========================================

> 이동욱 개발자의 '스프링부트로 웹 서비스 출시하기'를 참고하여
> 스프링부트 / JAP / AWS 관련 기술 학습을 목표로 한다.

### day 2-2 - TDD, 테스트코드 작성

---

- HelloController.class와 HelloControllerTest.class
  - HelloController에 어플리케이션 접속 확인을 위한 간단한 메소드를 작성하고, HelloControllerTest를 통해 해당 메소드가 정상적으로 동작하는지 테스트 한다.  
  - @RestController
    - 어노테이션이 선언 되어있는 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어준다. @Controller + @ResponseBody  
  - @RunWith(SpringRunner.class)
    - JUnit의 내장 실행자 외 다른 실행자를 실행
    - SpringRunner.class를 설정 함으로 Spring 실행자를 사용
    - 스프링 부트 테스트와 JUnit의 연결자 역할  
  - @WebMvcTest
    - 스프링 테스트 어노테이션 중 WebMvc에 집중할 수 있도록 지원하는 어노테이션  
  - MockMvc
    - Web API를 테스트 하기 위해 사용
    - HTTP GET, POST 등을 테스트 할 수 있음
    ```
      # Get 요청
      mvc.perform(get("/hello"))
      ...
      ;
    ```  
    
  - .andExpect(status.isOk())
    - mvc.perform에 대한 결과 검증
    - 인자로 받은 결과를 검증, 이 경우 HTTP Header의 status값을 검증한다.




- Junit @After annotation : 테스트 케이스 완료시 각각 호출
- Junit 5 부터 @After 어노테이션이 @AfterEach로 변경됨.
  - 그 외
  - @BeforeClass : 테스트 클래스 테스트 시작 시 1번만 호출 -> @BeforeAll
  - @Before : 테스트 케이스 시작전 각각 호출 -> @BeforeEach
  - @After : 테스트 케이스 완료 시 각각 호출 -> @AfterEach
  - @AfterClass : 테스트 클래스 모든 테스트 완료 시 1번 호출 -> @AfterAll
  
---

- 외부에서 전달되어진 데이터를 DB에 저장하기
  - @Autowired annotation을 사용하지 않는 이유 : 스프링 프레임워크 Bean 주입 방식 중 @Autowired 방식은 권장하지 않는 방식
  생성자를 사용하여 주입받는 방식이 권장된다. 이 때 @AllArgsConstructor annotation을 사용하여 모든 필드를 생성자를 통해 주입받을 수 있음
    extra-Autowired.md 참고 ( @Autowired 란 )
    
  - Entity 클래스와 유사한 RequestDto 클래스를 생성하여 사용하는 이유 : 
    테이블과 매핑되는 Entity 클래스는 Request/Response로 사용해서는 안된다.
    Entity를 기준으로 서비스의 비지니스 로직이 작동되기 때문에 Entity 클래스가 변경되면 서비스에 많은 영향을 주게된다.
    반면 Request/Response 용도의 Dto를 생성하면 이를 변경하여 View Layer와 DB Layer의 역할 분리
    