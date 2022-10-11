package com.capstone.projectory.follow;

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
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id", nullable = false)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private Long followeeId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private Long followerId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @Builder
    public Follow(Long followerId, Long followeeId) {
        Assert.notNull(followerId, "followerId must not be null");
        Assert.notNull(followeeId, "followeeId must not be null");
        this.followerId = followerId;
        this.followeeId = followeeId;
    }
}
