package com.capstone.projectory.post;


import com.capstone.projectory.user.User;
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
    @Column(nullable = false)
    private Long userId;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(name = "post_content", nullable = false)
    private String content;

    @Column(nullable = false)
    private int viewCount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date modifiedAt;

    @Builder
    public Post(Long userId, String title, String content) {
        Assert.notNull(userId, "userId must not be null");
        Assert.notNull(title, "title must not be null");
        Assert.notNull(content, "content must not be null");
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.viewCount = 0;
    }
}
