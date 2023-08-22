package com.capstone.member.domain;

import com.capstone.common.DateTimeEntity;
import com.capstone.notification.domain.FcmToken;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends DateTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean notifyOn;

    @OneToMany(mappedBy = "member", cascade = ALL, orphanRemoval = true)
    private List<FcmToken> records = new ArrayList();

    public void update(boolean notifyOn) {
        this.notifyOn = notifyOn;
    }

}
