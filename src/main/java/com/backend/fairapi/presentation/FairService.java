package com.backend.fairapi.presentation;

import com.backend.fairapi.presentation.dto.FairPartialDTO;
import com.backend.fairapi.domain.Fair;
import com.backend.fairapi.presentation.dto.FairCreateDTO;
import com.backend.fairapi.presentation.dto.FairUpdateDTO;
import com.backend.fairapi.presentation.dto.FairFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * The Fair service interface.
 */
public interface FairService {

    Fair create(FairCreateDTO createDTO);
    void saveAll(List<Fair> fairs);
    Fair getByRegisterCode(String registerCode);
    void delete(String registerCode);
    Fair update(String registerCode, FairUpdateDTO fairUpdateDTO);
    Fair updatePartial(String registerCode, FairPartialDTO partialDTO);
    Page<Fair> findAllByFilter(Pageable pageable, FairFilter filter);

}
