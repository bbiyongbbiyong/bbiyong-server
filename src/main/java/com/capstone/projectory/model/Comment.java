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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @NotNull
    private Long user_id;

    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "post_id")
    @NotNull
    private Long post_id;

    @Column(name = "comment_content", length = 200, nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date modified_at;

    @Builder
    public Comment(Long user_id, Long post_id, String content) {
        Assert.notNull(user_id, "userId must not be null");
        Assert.notNull(post_id, "postId must not be null");
        Assert.notNull(content, "content must not be null");
        this.user_id = user_id;
        this.post_id = post_id;
        this.content = content;
    }
}
