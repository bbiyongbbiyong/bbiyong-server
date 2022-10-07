package com.capstone.projectory.model;
import com.capstone.projectory.model.Converter.BooleanToYNConverter;

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
    private String profile_img;

    @Column(length = 30, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Provider provider;

    @Convert(converter = BooleanToYNConverter.class)
    @Column(nullable = false)
    private boolean is_student;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created_at;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date modified_at;

    @Builder
    public User(String name, String profile_img, String email, Provider provider, boolean is_student) {
        Assert.notNull(name, "name must not be null");
        Assert.notNull(profile_img, "profile_img must not be null");
        Assert.notNull(email, "email must not be null");
        Assert.notNull(provider, "provider must not be null");
        Assert.notNull(is_student, "is_student must not be null");
        this.name = name;
        this.profile_img = profile_img;
        this.email = email;
        this.provider = provider;
        this.is_student = is_student;
    }

    enum Provider {
        KAKAO
    }
}
