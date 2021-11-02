package com.kw.tutomato.webservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


// Mybatis 사용 시에 DAO 클래스와 같은 역할 ( DB Layer 접근자 )
// JpaRepository<> 를 상송하면 CRUD 메소드가 자동으로 생성된다.
// 필수! : Entity 클래스와 Entity Repository 인터페이스는 같은 지점에 위치해야한다.
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
