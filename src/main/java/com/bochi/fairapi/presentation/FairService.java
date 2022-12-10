package com.bochi.fairapi.presentation;

import com.bochi.fairapi.domain.Fair;
import com.bochi.fairapi.presentation.dto.FairCreateDTO;
import com.bochi.fairapi.presentation.dto.FairPartialDTO;
import com.bochi.fairapi.presentation.dto.FairUpdateDTO;
import com.bochi.fairapi.presentation.dto.FairFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The Fair service interface.
 */
public interface FairService {

    Fair create(FairCreateDTO createDTO);
    Fair getByRegisterCode(String registerCode);
    Fair getByName(String fairName);
    void delete(String registerCode);
    Fair update(String registerCode, FairUpdateDTO fairUpdateDTO);
    Fair updatePartial(String registerCode, FairPartialDTO partialDTO);
    Page<Fair> findAllByFilter(Pageable pageable, FairFilter filter);

}
