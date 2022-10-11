package com.capstone.projectory.postscrap;

import com.capstone.projectory.post.Post;
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
public class PostScrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postscrap_id", nullable = false)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private Long userId;

    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "post_id")
    @Column(nullable = false)
    private Long postId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @Builder
    public PostScrap(Long userId, Long postId) {
        Assert.notNull(userId, "userId must not be null");
        Assert.notNull(postId, "postId must not be null");
        this.userId = userId;
        this.postId = postId;
    }
}