package com.bochi.fairapi.fair;

import com.bochi.fairapi.fair.dto.FairCreateDTO;
import com.bochi.fairapi.fair.dto.FairPartialDTO;
import com.bochi.fairapi.fair.dto.FairUpdateDTO;
import com.bochi.fairapi.fair.dto.FairFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FairService {

    Fair create(FairCreateDTO createDTO);
    Fair getByRegisterCode(String registerCode);
    Fair getByName(String fairName);
    void delete(String registerCode);
    void softDelete(String registerCode);
    Fair update(String registerCode, FairUpdateDTO fairUpdateDTO);
    Fair updatePartial(String registerCode, FairPartialDTO partialDTO);
    Page<Fair> findAllByFilter(Pageable pageable, FairFilter filter);

}
