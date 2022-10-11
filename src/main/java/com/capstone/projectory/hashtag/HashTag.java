package com.capstone.projectory.hashtag;

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
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id", nullable = false)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private Long userId;

    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "post_id")
    @Column(nullable = false)
    private Long postId;

    @Column(name = "hashtag_content", length = 50, nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @Builder
    public HashTag(Long userId, Long postId, String content) {
        Assert.notNull(content, "hashtag must not be null");
        this.userId = userId;
        this.postId = postId;
        this.content = content;
    }
}
