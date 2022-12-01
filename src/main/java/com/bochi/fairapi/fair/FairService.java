package com.bochi.fairapi.fair;

import com.bochi.fairapi.fair.dto.FairDTO;
import com.bochi.fairapi.fair.dto.FairFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FairService {

    Fair create(Fair fair);
    void delete(String registerCode);
    Fair update(FairDTO fairDTO);
    Page<Fair> findAllByFilter(Pageable pageable, FairFilter filter);

}
