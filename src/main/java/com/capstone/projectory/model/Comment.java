package com.capstone.projectory.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Long user_id;

    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "post_id")
    private Long post_id;

    @Column(length = 200, nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date modified_at;

    @Builder
    public Comment(Long user_id, Long post_id, String content) {
        this.user_id = user_id;
        this.post_id = post_id;
        this.content = content;
    }
}
