package com.capstone.projectory.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Long user_id;

    @ManyToOne(targetEntity = Post.class)
    @JoinColumn(name = "post_id")
    private Long post_id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created_at;

    @Builder
    public PostScrap(Long user_id, Long post_id) {
        this.user_id = user_id;
        this.post_id = post_id;
    }
}