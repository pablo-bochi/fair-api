package com.bochi.fairapi.fair;

import com.bochi.fairapi.fair.dto.FairDTO;
import com.bochi.fairapi.fair.dto.FairFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FairServiceImpl implements FairService {

    @Override
    public Fair create(Fair fair) {
        return null;
    }

    @Override
    public void delete(String registerCode) {

    }

    @Override
    public Fair update(FairDTO fairDTO) {
        return null;
    }

    @Override
    public Page<Fair> findAllByFilter(Pageable pageable, FairFilter filter) {
        return null;
    }

}
