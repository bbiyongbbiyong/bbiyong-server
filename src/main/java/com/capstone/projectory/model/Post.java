package com.capstone.projectory.model;


import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

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
    @NotNull
    private Long user_id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(name = "post_content", nullable = false)
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
        Assert.notNull(user_id, "userId must not be null");
        Assert.notNull(title, "title must not be null");
        Assert.notNull(content, "content must not be null");
        this.user_id = user_id;
        this.title = title;
        this.content = content;
        this.view_count = 0;
    }
}
