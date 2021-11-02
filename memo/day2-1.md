- 스프링부트로 웹 서비스 츨시하기 Tutorial
  ========================================

> 이동욱 개발자의 '스프링부트로 웹 서비스 출시하기'를 참고하여
> 스프링부트 / JAP / AWS 관련 기술 학습을 목표로 한다.

### day 2 - springBoot & jap 로 간단한 api 생성

1. domain 코드생성
 - 실제 DB 테이블과 매칭될 Entity 클래스를 생성한다.
    - JPA 어노테이션
    - @Entity : 테이블과 링크 될 클래스임을 명시 / 언더스코어 네이밍 
    - @Id : 해당 테이블의 PK 필드
    - @GeneratedValue : PK생성 규칙 명시 / 기본값 Auto / Springboot 2.x 사용 시 별도 옵션 추가 필요
    - @Column : 테이블의 컬럼을 나타냄 ( 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이 됨 ) / 기본값 외 추가변경이 필요할 경우 사용
     
  - Lombook 어노테이션
    - @NoArgsConstructor : 기본 생성자 자동 추가
        - access = AccessLevel.PROTECTED : 기본 생성자의 접근 제한 설정
        - 생성자로 protected Post(){} 와 동일하게 작용
        - Protected 설정 이유 : 프로젝트 코드상에서 기본 생성자로 생성하는 것은 막되, JPA에서 Entity 클래스를 생성할 수 있도록 허용하기 위함
    - @Getter : 클래스 내 모든 필드의 Getter 메소드를 자동 생성
    - @Builder : 해당 클래스의 빌더 패턴 클래스 생성
    
2. Entity 클래스의 인스턴스 값들이 변화하는 것을 코드상으로 구분하기 위해 @Setter 사용 않고 @Builder 사용 
```
잘못된 사용
public class Order{
    public void setStatus(boolean status){
        this.status = status
    }
}

public void 주문서비스의_취소메소드 (){
   order.setStatus(false);
}

올바른 사용
public class Order{
    public void cancelOrder(){
        this.status = false;
    }
}
public void 주문서비스의_취소메소드 (){
   order.cancelOrder();
}
```

# !중요 : 
기본적인 구조는 생성자를 통해 최종 값을 채운후 DB에 Insert 하는것이며, 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출하여 변경하는 것을 전제로 합니다.
여기서 생성자 대신에 @Builder를 통해 제공되는 빌더 클래스를 사용합니다.
생성자나 빌더나 생성시점에 값을 채워주는 역할은 똑같습니다.
다만, 생성자의 경우 지금 채워야할 필드가 무엇인지 명확히 지정할수가 없습니다.

---