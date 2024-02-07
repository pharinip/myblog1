package com.myblog.myblog1.payload;


import com.myblog.myblog1.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private long id;

    private String title;
    private String  description;
    private String content;


}
