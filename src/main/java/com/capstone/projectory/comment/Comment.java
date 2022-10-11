package com.capstone.projectory.comment;

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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private Long userId;

    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "post_id")
    @Column(nullable = false)
    private Long postId;

    @Column(name = "comment_content", length = 200, nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date modifiedAt;

    @Builder
    public Comment(Long userId, Long postId, String content) {
        Assert.notNull(userId, "userId must not be null");
        Assert.notNull(postId, "postId must not be null");
        Assert.notNull(content, "content must not be null");
        this.userId = userId;
        this.postId = postId;
        this.content = content;
    }
}
