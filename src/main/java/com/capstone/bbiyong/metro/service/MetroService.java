package com.capstone.bbiyong.metro.service;

import com.capstone.bbiyong.metro.domain.Metro;
import com.capstone.bbiyong.metro.dto.MetroResponseDTO;
import com.capstone.bbiyong.metro.repository.MetroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Transactional
    public List<MetroResponseDTO> getMetro(LocalDateTime now) {
        return metroRepository.findAllBy(now).stream()
                .map(MetroResponseDTO::from)
                .collect(Collectors.toList());
    }
}
