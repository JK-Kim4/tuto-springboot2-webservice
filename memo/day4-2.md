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




