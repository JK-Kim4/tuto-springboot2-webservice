package com.kw.tutomato.webservice.service;

import com.kw.tutomato.webservice.domain.user.Role;
import com.kw.tutomato.webservice.domain.user.User;
import com.kw.tutomato.webservice.domain.user.UserRepository;
import com.kw.tutomato.webservice.web.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Transactional
    public String update(UserUpdateDto userUpdateDto){
        User user = userRepository.findByEmail(userUpdateDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. email =" + userUpdateDto.getEmail()));

        log.info("service@selectUserEmail : " + user.getEmail());

        user.updateRole(Role.USER);

        httpSession.invalidate();

        return user.getEmail();

    }
}
