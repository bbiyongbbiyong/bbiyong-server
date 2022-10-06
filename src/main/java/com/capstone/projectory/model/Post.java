package com.capstone.projectory.model;


import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private Long user_id;

    @Column(length = 50, nullable = false)
    private String title;

    @NotNull
    private String content;

    @NotNull
    private int view_count;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date modified_at;

    @Builder
    public Post(Long user_id, String title, String content) {
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.view_count = 0;
    }
}
