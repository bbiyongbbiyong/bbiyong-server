package com.capstone.location.init;

import com.capstone.location.domain.Location;
import com.capstone.location.repository.LocationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class InitLocation {

    private static final List<String> locationNames = List.of("전체", "강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구",
            "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구");
    private final LocationRepository tagRepository;

    //@PostConstruct
    public void init() {
        List<Location> tags = locationNames.stream()
                .map(Location::new)
                .collect(Collectors.toList());
        tagRepository.saveAll(tags);
    }

}