package com.capstone.bbiyong.location.init;

import com.capstone.bbiyong.location.domain.Location;
import com.capstone.bbiyong.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class InitLocation {

    private static final List<String> locationNames = List.of("전체", "도봉구", "은평구",
            "동대문구", "동작구", "금천구", "구로구", "종로구", "강북구", "중랑구", "강남구", "강서구", "중구", "강동구", "광진구", "마포구", "관악구");
    private final LocationRepository tagRepository;

    @PostConstruct
    public void init() {
        List<Location> tags = locationNames.stream()
                .map(Location::new)
                .collect(Collectors.toList());
        tagRepository.saveAll(tags);
    }

}