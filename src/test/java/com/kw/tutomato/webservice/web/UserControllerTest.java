package com.kw.tutomato.webservice.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kw.tutomato.webservice.domain.user.Role;
import com.kw.tutomato.webservice.domain.user.User;
import com.kw.tutomato.webservice.domain.user.UserRepository;
import com.kw.tutomato.webservice.web.dto.UserUpdateDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception{
        userRepository.deleteAll();
    }

    @Test
    public void 상태_수정() throws Exception{
        //given
        User saveUser = userRepository.save(
                User.builder()
                        .role(Role.GUEST)
                        .picture("null")
                        .name("test")
                        .email("test@gmail.com")
                        .build()
        );
        String url = "http://localhost:" + port + "/user/update/state";
        String email = "test@gmail.com";

        UserUpdateDto updateDto = UserUpdateDto.builder()
                .email(email)
                .build();
        //when
        mvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(updateDto)))
                .andExpect(status().isOk());
        //then

        List<User> all = userRepository.findAll();
        assertThat(all.get(0).getRole()).isEqualTo(Role.USER);
    }
}
