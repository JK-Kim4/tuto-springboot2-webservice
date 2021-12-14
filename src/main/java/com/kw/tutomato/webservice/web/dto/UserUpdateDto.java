package com.kw.tutomato.webservice.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateDto {

    private String email;

    @Builder
    public UserUpdateDto(String email){
        this.email = email;
    }

}
