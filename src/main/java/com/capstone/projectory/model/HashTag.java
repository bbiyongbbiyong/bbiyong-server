package com.capstone.projectory.model;

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
    private Long user_id;

    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "post_id")
    private Long post_id;

    @Column(name = "hashtag_content", length = 50, nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created_at;

    @Builder
    public HashTag(Long user_id, Long post_id, String content) {
        Assert.notNull(content, "hashtag must not be null");
        this.user_id = user_id;
        this.post_id = post_id;
        this.content = content;
    }
}
