package com.kw.tutomato.webservice.web.dto;

import com.kw.tutomato.webservice.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
