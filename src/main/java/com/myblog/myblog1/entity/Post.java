package com.myblog.myblog1.entity;


import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.*;

import java.util.List;


@Entity
@Table(name="posts")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private long id;

    private String title;
    private String  description;
     private String content;

     //one post can have multiple comments
    @OneToMany(cascade=CascadeType.ALL,mappedBy="post")
    private List<Comment> comments;

}
