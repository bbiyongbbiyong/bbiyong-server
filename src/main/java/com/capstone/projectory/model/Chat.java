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
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "char_id", nullable = false)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @NotNull
    private Long sender_id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    @NotNull
    private Long receiver_id;

    @Column(name = "chat_content", length = 100, nullable = false)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date created_at;

    @Builder
    public Chat(Long sender_id, Long receiver_id, String content) {
        Assert.notNull(sender_id, "senderId must not be null");
        Assert.notNull(receiver_id, "receiverId must not be null");
        Assert.notNull(content, "content must not be null");
        this.sender_id = sender_id;
        this.receiver_id = receiver_id;
        this.content = content;
    }
}
