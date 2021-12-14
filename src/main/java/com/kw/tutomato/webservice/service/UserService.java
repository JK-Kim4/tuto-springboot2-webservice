package com.kw.tutomato.webservice.service;

import com.kw.tutomato.webservice.domain.user.Role;
import com.kw.tutomato.webservice.domain.user.User;
import com.kw.tutomato.webservice.domain.user.UserRepository;
import com.kw.tutomato.webservice.web.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String update(UserUpdateDto userUpdateDto){
        User user = userRepository.findByEmail(userUpdateDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. email =" + userUpdateDto.getEmail()));

        user.updateRole(Role.USER);

        return userUpdateDto.getEmail();
    }
}
