package com.bochi.fairapi.domain;

import com.bochi.fairapi.domain.Fair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * The interface Fair repository.
 */
public interface FairRepository extends JpaRepository<Fair, Long>, JpaSpecificationExecutor<Fair> {

    /**
     * Find by register code and active optional.
     *
     * @param registerCode the register code
     * @param active       the active
     * @return the optional
     */
    Optional<Fair> findByRegisterCodeAndActive(String registerCode, Boolean active);

}
