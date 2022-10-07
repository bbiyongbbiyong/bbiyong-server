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
public class PostScrap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postscrap_id", nullable = false)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @NotNull
    private Long user_id;

    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "post_id")
    @NotNull
    private Long post_id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created_at;

    @Builder
    public PostScrap(Long user_id, Long post_id) {
        Assert.notNull(user_id, "userId must not be null");
        Assert.notNull(post_id, "postId must not be null");
        this.user_id = user_id;
        this.post_id = post_id;
    }
}