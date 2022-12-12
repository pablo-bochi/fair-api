package com.backend.fairapi.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import java.util.Optional;

/**
 * The Fair repository interface.
 */
public interface FairRepository extends JpaRepository<Fair, Long>, JpaSpecificationExecutor<Fair> {

    /**
     * Find optional of Fair by register code
     *
     * @param registerCode Fair's register code
     * @return the optional
     */
    Optional<Fair> findByRegisterCode(String registerCode);

}
