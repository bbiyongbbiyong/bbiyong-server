package com.capstone.projectory.chat;

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
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "char_id", nullable = false)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private Long senderId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @Column(nullable = false)
    private Long receiverId;

    @Column(name = "chat_content", length = 100, nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @Builder
    public Chat(Long senderId, Long receiverId, String content) {
        Assert.notNull(senderId, "senderId must not be null");
        Assert.notNull(receiverId, "receiverId must not be null");
        Assert.notNull(content, "content must not be null");
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
    }
}
