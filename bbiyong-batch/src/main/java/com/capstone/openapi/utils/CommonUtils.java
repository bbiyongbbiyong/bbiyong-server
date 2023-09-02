package com.capstone.openapi.utils;

import com.capstone.member.domain.Member;
import com.capstone.member.repository.MemberRepository;
import com.capstone.notification.repository.FcmTokenRepository;
import com.capstone.notification.service.FirebaseService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommonUtils {

    private final FcmTokenRepository fcmTokenRepository;
    private final MemberRepository memberRepository;
    private final FirebaseService firebaseService;

    public CommonUtils(FcmTokenRepository fcmTokenRepository, MemberRepository memberRepository, FirebaseService firebaseService) {
        this.fcmTokenRepository = fcmTokenRepository;
        this.memberRepository = memberRepository;
        this.firebaseService = firebaseService;
    }

    public void sendNotificationInfo(String topic, String info) {

        if (topic.isEmpty())
            return ;

        List<Member> members = memberRepository.findAllBySubscribe(topic);
        if (members.isEmpty())
            return ;
        List<String> fcm_token = new ArrayList<>();
        for (int i = 0; i < members.size(); i++) {
            List<String> tokens = fcmTokenRepository.findAllByMemberAndSubscribe(members.get(i));
            fcm_token.addAll(tokens);
        }
        if (fcm_token.isEmpty())
            return ;
        firebaseService.sendNotification(fcm_token, "삐용삐용", info);
    }
}
