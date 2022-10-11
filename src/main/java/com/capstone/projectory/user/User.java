package com.capstone.projectory.user;

import com.capstone.projectory.user.converter.BooleanToYNConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String profileImg;

    @Column(length = 30, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Provider provider;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(nullable = false)
    private boolean isStudent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date modifiedAt;

    @Builder
    public User(String name, String profileImg, String email, Provider provider, boolean isStudent) {
        Assert.notNull(name, "name must not be null");
        Assert.notNull(profileImg, "profileImg must not be null");
        Assert.notNull(email, "email must not be null");
        Assert.notNull(provider, "provider must not be null");
        Assert.notNull(isStudent, "isStudent must not be null");
        this.name = name;
        this.profileImg = profileImg;
        this.email = email;
        this.provider = provider;
        this.isStudent = isStudent;
    }

    enum Provider {
        KAKAO
    }
}
