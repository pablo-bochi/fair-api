package com.bochi.fairapi.fair;

import com.bochi.fairapi.core.exception.FieldError;
import com.bochi.fairapi.core.exception.InvalidInputException;
import com.bochi.fairapi.core.exception.ResourceAlreadyExistsException;
import com.bochi.fairapi.core.exception.ResourceNotFoundException;
import com.bochi.fairapi.fair.dto.FairCreateDTO;
import com.bochi.fairapi.fair.dto.FairPartialDTO;
import com.bochi.fairapi.fair.dto.FairUpdateDTO;
import com.bochi.fairapi.fair.dto.FairFilter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bochi.fairapi.fair.FairSpecification.*;

@Slf4j
@Service
@AllArgsConstructor
public class FairServiceImpl implements FairService {

    private final FairRepository fairRepository;

    /**
     * *
     * @param createDTO DTO de criacao de feira
     * @return Objeto feira
     */
    @Override
    public Fair create(FairCreateDTO createDTO) {
        Fair fair = Fair.of(createDTO);
        validateUnique(fair);
        try {
            return fairRepository.save(fair);
        } catch (ConstraintViolationException e) {
            List<FieldError> errors = formatConstrainsViolation(e);
            throw new InvalidInputException(errors);
        }
    }

    /**
     * *
     * @param registerCode código de registro
     * @return objeto feira
     */
    @Override
    public Fair getByRegisterCode(String registerCode) {
        return fairRepository.findByRegisterCodeAndActive(registerCode, Boolean.TRUE)
                .orElseThrow(() -> new ResourceNotFoundException("Feira não encontrada para registro: " + registerCode));
    }

    /**
     *
     * @param fairName nome da feira
     * @return objeto feira
     */
    @Override
    public Fair getByName(String fairName) {
        return fairRepository.findOne(active(Boolean.TRUE).and(fairName(fairName)))
                .orElseThrow(() -> new ResourceNotFoundException("Feira não encontrada para o nome: " + fairName));
    }

    /**
     * *
     * @param pageable objeto de paginacao
     * @param filter filtros da feira
     * @return pagina de feira
     */
    @Override
    public Page<Fair> findAllByFilter(Pageable pageable, FairFilter filter) {
        Specification<Fair> specification = active(Boolean.TRUE);

        if (Objects.nonNull(filter.getDistrict()))
            specification = specification.and(district(filter.getDistrict()));

        if (Objects.nonNull(filter.getRegion5()))
            specification = specification.and(region5(filter.getRegion5()));

        if (Objects.nonNull(filter.getFairName()))
            specification = specification.and(fairName(filter.getFairName()));

        if (Objects.nonNull(filter.getNeighbourhood()))
            specification = specification.and(neighbourhood(filter.getNeighbourhood()));

        return fairRepository.findAll(specification, pageable);
    }

    /**
     * *
     * @param registerCode codigo de registro
     * @return optional de feira
     */
    public Optional<Fair> findByRegisterCode(String registerCode) {
        return fairRepository.findByRegisterCodeAndActive(registerCode, Boolean.TRUE);
    }

    /**
     * Hard delete - remover o registro de uma feira do banco*
     * @param registerCode Código de registro
     */
    @Override
    public void delete(String registerCode) {
        Fair fair = this.getByRegisterCode(registerCode);
        fairRepository.delete(fair);
    }

    /**
     * soft delete - inativar uma feira
     * @param registerCode codigo de registro
     */
    @Override
    public void softDelete(String registerCode) {
        Fair fair = this.getByRegisterCode(registerCode);
        fairRepository.save(fair.toBuilder()
                .active(Boolean.FALSE)
                .build());
    }

    /**
     * *
     * @param registerCode codigo de registro
     * @param fairUpdateDTO DTO de atualizacao de uma feira
     * @return objeto feira
     */
    @Override
    public Fair update(String registerCode, FairUpdateDTO fairUpdateDTO) {
        Fair fair = this.getByRegisterCode(registerCode);
        assert fair != null;
        try {
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
     * *
     * @param registerCode codigo de registro
     * @param partialDTO DTO para determinar qual atributo sera atualizado
     * @return objeto feira
     */
    @Override
    public Fair updatePartial(String registerCode, FairPartialDTO partialDTO) {
        Fair fair = this.getByRegisterCode(registerCode);
        assert fair != null;

        if (partialDTO.getField().equalsIgnoreCase("longitude")) {
            fair.setLon(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("latitude")) {
            fair.setLat(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("setor_censitario")) {
            fair.setCensusSector(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("area_ponderada")) {
            fair.setWightedArea(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("cod_distrito")) {
            fair.setDistrictCode(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("distrito")) {
            fair.setDistrict(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("cod_subprefeitura")) {
            fair.setSubPrefectureCode(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("subprefeitura")) {
            fair.setSubPrefecture(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("regiao_5")) {
            fair.setRegion5(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("regiao_8")) {
            fair.setRegion8(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("nome_feira")) {
            fair.setLon(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("logradouro")) {
            fair.setAddress(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("numero")) {
            fair.setNumber(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("bairro")) {
            fair.setNeighbourhood(partialDTO.getValue());
        }

        if (partialDTO.getField().equalsIgnoreCase("referencia")) {
            fair.setRefPoint(partialDTO.getValue());
        }

        return fairRepository.save(fair);
    }

    /**
     * *
     * @param e excecao
     * @return lista formatada de erros
     */
    private List<FieldError> formatConstrainsViolation(ConstraintViolationException e){
        Map<Object, List<String>> messages = e.getConstraintViolations().stream().collect(
                Collectors.groupingBy(ConstraintViolation::getPropertyPath,
                        Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())));

        return messages.entrySet().stream().map(
                entry -> new FieldError(entry.getKey().toString(), entry.getValue())).collect(Collectors.toList());
    }

    /**
     * *
     * @param fair objeto feira
     */
    private void validateUnique(Fair fair) {
        this.findByRegisterCode(fair.getRegisterCode()).ifPresent(it -> {
            throw new ResourceAlreadyExistsException("Feira já existe para o registro: " + fair.getRegisterCode());
        });
    }

}
