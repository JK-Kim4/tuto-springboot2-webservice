package com.kw.tutomato.webservice.web;

import com.kw.tutomato.webservice.config.oauth.LoginUser;
import com.kw.tutomato.webservice.config.oauth.dto.SessionUser;
import com.kw.tutomato.webservice.service.PostsService;
import com.kw.tutomato.webservice.service.UserService;
import com.kw.tutomato.webservice.web.dto.PostsResponseDto;
import com.kw.tutomato.webservice.web.dto.UserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;
    private final UserService userService;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user){
        log.debug("findAllDesc :: {}", postsService.findAllDesc());
        model.addAttribute("posts", postsService.findAllDesc());
        if(user != null){
            model.addAttribute("userName", user.getName());
            model.addAttribute("userEmail", user.getEmail());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @ResponseBody
    @PutMapping("/user/update/state")
    public String updateState(@RequestBody UserUpdateDto userUpdateDto){
        return userService.update(userUpdateDto);
    }
}
