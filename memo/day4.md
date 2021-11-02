- 스프링부트로 웹 서비스 츨시하기 Tutorial
  ========================================

> 이동욱 개발자의 '스프링부트로 웹 서비스 출시하기'를 참고하여
> 스프링부트 / JAP / AWS 관련 기술 학습을 목표로 한다.

### day 4 - view단 구성하기

---

 - index 페이지 작성 후 게시글 insert / findAll 구현
    - JPA 사용하여 findAllDesc() 구현 중, 계속해서 NullPointException이 발생함 : (해결) indexController에서 PostsService를 주입하는 과정에서
    생성자에 대한 정의가 되어있지 않아 정상작동 하지 않았음 -> @RequriedArgsContstructor 추가하여 해결