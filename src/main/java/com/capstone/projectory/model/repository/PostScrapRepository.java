package com.capstone.projectory.model.repository;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.stereotype.Repository;
import com.capstone.projectory.model.PostScrap;

@Repository
public interface PostScrapRepository extends JpaAttributeConverter<PostScrap, Long> {
}
