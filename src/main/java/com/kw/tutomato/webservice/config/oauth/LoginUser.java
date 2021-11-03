package com.kw.tutomato.webservice.config.oauth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) //해당 어노테이션이 생성 될 수 있는 위치를 지정 ( Paramter로 선언된 객체에 사용 )
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}
