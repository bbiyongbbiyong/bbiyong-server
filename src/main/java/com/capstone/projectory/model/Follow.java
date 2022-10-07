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
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id", nullable = false)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @NotNull
    private Long followee_id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @NotNull
    private Long follower_id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created_at;

    @Builder
    public Follow(Long follower_id, Long followee_id) {
        Assert.notNull(follower_id, "followerId must not be null");
        Assert.notNull(followee_id, "followeeId must not be null");
        this.follower_id = follower_id;
        this.followee_id = followee_id;
    }
}
