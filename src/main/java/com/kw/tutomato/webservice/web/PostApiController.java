package com.kw.tutomato.webservice.web;

import com.kw.tutomato.webservice.service.PostsService;
import com.kw.tutomato.webservice.web.dto.PostsResponseDto;
import com.kw.tutomato.webservice.web.dto.PostsSaveRequestDto;
import com.kw.tutomato.webservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {

    private final PostsService postsService;

    //INSERT
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    //UPDATE
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    //GET
    @GetMapping("/api/vi/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    //DELETE
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

}
