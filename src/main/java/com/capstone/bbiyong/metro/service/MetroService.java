package com.capstone.bbiyong.metro.service;

import com.capstone.bbiyong.metro.domain.Metro;
import com.capstone.bbiyong.metro.repository.MetroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MetroService {

    private final MetroRepository metroRepository;

    @Transactional
    public void addMetro(Metro newMetro) {
        Long tweetId = newMetro.getTweetId();
        Optional<Metro> metro = metroRepository.findByTweetId(tweetId);
        if (metro.isEmpty())
            metroRepository.save(newMetro);
    }
}
