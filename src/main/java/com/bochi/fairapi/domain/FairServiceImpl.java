package com.bochi.fairapi.domain;

import com.bochi.fairapi.core.exception.FieldError;
import com.bochi.fairapi.core.exception.InvalidInputException;
import com.bochi.fairapi.core.exception.ResourceAlreadyExistsException;
import com.bochi.fairapi.core.exception.ResourceNotFoundException;
import com.bochi.fairapi.presentation.dto.FairCreateDTO;
import com.bochi.fairapi.presentation.dto.FairPartialDTO;
import com.bochi.fairapi.presentation.dto.FairUpdateDTO;
import com.bochi.fairapi.presentation.dto.FairFilter;
import com.bochi.fairapi.presentation.FairService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.bochi.fairapi.domain.FairSpecification.*;

@Slf4j
@Service
@AllArgsConstructor
public class FairServiceImpl implements FairService {

    private final FairRepository fairRepository;

    /**
     * Creates a new fair, validating if the fair isn't already created and validating its constraints
     * @param createDTO fair creation DTO
     * @return created Fair
     */
    @Override
    @Transactional
    public Fair create(FairCreateDTO createDTO) {
        Fair fair = Fair.of(createDTO);
        log.info("Validating unique fair for register={}", createDTO.getRegisterCode());
        validateUnique(fair);
        try {
            log.info("Saving new fair");
            return fairRepository.save(fair);
        } catch (ConstraintViolationException e) {
            List<FieldError> errors = formatConstrainsViolation(e);
            throw new InvalidInputException(errors);
        }
    }

    /**
     * Saves a list of fair
     * @param fairs fair list
     */
    @Override
    @Transactional
    public void saveAll(List<Fair> fairs) {
        fairRepository.saveAll(fairs);
    }

    /**
     * Gets a Fair by its registerCode
     * @param registerCode Fair's registerCode
     * @return found Fair
     */
    @Override
    public Fair getByRegisterCode(String registerCode) {
        log.info("Searching fair by register code={}", registerCode);
        return fairRepository.findByRegisterCode(registerCode)
                .orElseThrow(() -> new ResourceNotFoundException("Feira não encontrada para registro: " + registerCode));
    }

    /**
     * Finds a page of Fairs filtered by district, region5, fairName and/or neighbourhood
     * @param pageable Pagination info, such as page number and size
     * @param filter Fair filters
     * @return Fair page filtered
     */
    @Override
    public Page<Fair> findAllByFilter(Pageable pageable, FairFilter filter) {
        Specification<Fair> specification = null;

        if (Objects.nonNull(filter.getDistrict())) {
            log.info("Filtering by district");
            specification = district(filter.getDistrict());
        }

        if (Objects.nonNull(filter.getRegion5())) {
            log.info("Filtering by region5");
            specification = Objects.isNull(specification) ?
                    region5(filter.getRegion5()) :
                    specification.and(region5(filter.getRegion5()));
        }

        if (Objects.nonNull(filter.getFairName())) {
            log.info("Filtering by name");
            specification = Objects.isNull(specification) ?
                    fairName(filter.getFairName()) :
                    specification.and(fairName(filter.getFairName()));
        }

        if (Objects.nonNull(filter.getNeighbourhood())) {
            log.info("Filtering by neighbourhood");
            specification = Objects.isNull(specification) ?
                    neighbourhood(filter.getNeighbourhood()) :
                    specification.and(neighbourhood(filter.getNeighbourhood()));
        }

        return fairRepository.findAll(specification, pageable);
    }

    /**
     * Removes a fair by its register code
     * @param registerCode Fair's register code
     */
    @Override
    @Transactional
    public void delete(String registerCode) {
        Fair fair = this.getByRegisterCode(registerCode);
        log.info("Deleting fair with register code={}", registerCode);
        fairRepository.delete(fair);
    }

    /**
     * Updates a Fair by its register code
     * @param registerCode Fair's register code
     * @param fairUpdateDTO Update DTO with the fair updated info
     * @return Updated Fair
     */
    @Override
    @Transactional
    public Fair update(String registerCode, FairUpdateDTO fairUpdateDTO) {
        Fair fair = this.getByRegisterCode(registerCode);
        assert fair != null;
        try {
            log.info("Updating fair with register code={}", registerCode);
            return fairRepository.save(fair.toBuilder()
                    .lon(fairUpdateDTO.getLon())
                    .lat(fairUpdateDTO.getLat())
                    .censusSector(fairUpdateDTO.getCensusSector())
                    .wightedArea(fairUpdateDTO.getWightedArea())
                    .districtCode(fairUpdateDTO.getDistrictCode())
                    .district(fairUpdateDTO.getDistrict())
                    .subPrefectureCode(fairUpdateDTO.getSubPrefectureCode())
                    .subPrefecture(fairUpdateDTO.getSubPrefecture())
                    .region5(fairUpdateDTO.getRegion5())
                    .region8(fairUpdateDTO.getRegion8())
                    .fairName(fairUpdateDTO.getFairName())
                    .address(fairUpdateDTO.getAddress())
                    .number(fairUpdateDTO.getNumber())
                    .neighbourhood(fairUpdateDTO.getNeighbourhood())
                    .refPoint(fairUpdateDTO.getRefPoint())
                    .build());
        } catch (ConstraintViolationException e) {
            List<FieldError> errors = formatConstrainsViolation(e);
            throw new InvalidInputException(errors);
        }
    }

    /**
     * Updates a single field of the Fair
     * @param registerCode Fair's register code
     * @param partialDTO DTO containing the field to be updated and its value
     * @return Updated Fair
     */
    @Override
    @Transactional
    public Fair updatePartial(String registerCode, FairPartialDTO partialDTO) {
        Fair fair = this.getByRegisterCode(registerCode);
        assert fair != null;

        if (partialDTO.getField().equalsIgnoreCase("LONG")) {
            log.info("Updating lon");
            fair.setLon(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("LAT")) {
            log.info("Updating lat");
            fair.setLat(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("SETCENS")) {
            log.info("Updating censusSector");
            fair.setCensusSector(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("AREAP")) {
            log.info("Updating weightedArea");
            fair.setWightedArea(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("CODDIST")) {
            log.info("Updating districtCode");
            fair.setDistrictCode(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("DISTRITO")) {
            log.info("Updating district");
            fair.setDistrict(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("CODSUBPREF")) {
            log.info("Updating subPrefectureCode");
            fair.setSubPrefectureCode(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("SUBPREFE")) {
            log.info("Updating subPrefecture");
            fair.setSubPrefecture(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("REGIAO5")) {
            log.info("Updating region5");
            fair.setRegion5(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("REGIAO8")) {
            log.info("Updating region8");
            fair.setRegion8(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("NOME_FEIRA")) {
            log.info("Updating fairName");
            fair.setFairName(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("LOGRADOURO")) {
            log.info("Updating address");
            fair.setAddress(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("NUMERO")) {
            log.info("Updating number");
            fair.setNumber(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("BAIRRO")) {
            log.info("Updating neighbourhood");
            fair.setNeighbourhood(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("REFERENCIA")) {
            log.info("Updating refPoint");
            fair.setRefPoint(partialDTO.getValue());
        }

        return fairRepository.save(fair);
    }

    /**
     * Formats the constraint violations for better understanding of the exception message
     * @param e ConstraintViolationException object
     * @return Formatted errors list
     */
    private List<FieldError> formatConstrainsViolation(ConstraintViolationException e){
        Map<Object, List<String>> messages = e.getConstraintViolations().stream().collect(
                Collectors.groupingBy(ConstraintViolation::getPropertyPath,
                        Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())));

        return messages.entrySet().stream().map(
                entry -> new FieldError(entry.getKey().toString(), entry.getValue())).collect(Collectors.toList());
    }

    /**
     * Validates if the given Fair object is unique by its register code
     * @param fair Fair to be validated
     */
    private void validateUnique(Fair fair) {
        log.info("Searching fair by register code={}", fair.getRegisterCode());
        fairRepository.findByRegisterCode(fair.getRegisterCode()).ifPresent(it -> {
            throw new ResourceAlreadyExistsException("Feira já existe para o registro: " + fair.getRegisterCode());
        });
    }

}
