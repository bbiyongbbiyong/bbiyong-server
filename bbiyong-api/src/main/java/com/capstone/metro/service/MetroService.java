package com.capstone.metro.service;

import com.capstone.metro.Metro;
import com.capstone.metro.dto.MetroResponseDTO;
import com.capstone.metro.repository.MetroRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        Long openapiId = newMetro.getOpenapiId();
        Optional<Metro> metro = metroRepository.findByOpenapiId(openapiId);
        if (metro.isEmpty())
            metroRepository.save(newMetro);
    }

    @Transactional
    public List<MetroResponseDTO> getMetro(LocalDateTime now) {
        return metroRepository.findAllBy(now).stream()
                .map(MetroResponseDTO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteMetros() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime aWeekAgo = now.minusDays(7);

        metroRepository.deleteAllMetrosByEndDate(aWeekAgo);
    }
}
