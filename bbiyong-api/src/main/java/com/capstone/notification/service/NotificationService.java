package com.capstone.notification.service;

import com.capstone.member.domain.Member;
import com.capstone.notification.domain.FcmToken;
import com.capstone.notification.dto.FcmTokenRequestDTO;
import com.capstone.notification.repository.FcmTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final FcmTokenRepository fcmTokenRepository;
    @Transactional
    public void saveFcmToken(Member member, FcmTokenRequestDTO requestDTO) {
        Optional<FcmToken> token = fcmTokenRepository.findByMemberAndToken(member, requestDTO.getToken());

        if (token.isEmpty())
        {
            var fcmToken = FcmToken.builder()
                    .token(requestDTO.getToken())
                    .member(member)
                    .build();

            fcmTokenRepository.save(fcmToken);
        }
    }

    @Transactional
    public void deleteFcmToken(List<String> tokens) {
        fcmTokenRepository.deleteAllByToken(tokens);
    }
}
