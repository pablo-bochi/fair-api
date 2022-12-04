package com.bochi.fairapi.fair;

import com.bochi.fairapi.fair.dto.FairCreateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * The type Fair.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
@Builder(toBuilder = true)
public class Fair {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "long")
    private String lon;

    @Column(name = "lat")
    private String lat;

    @Column(name = "setcens")
    private String censusSector;

    @Column(name = "areap")
    private String wightedArea;

    @Column(name = "coddist")
    private String districtCode;

    @Column(name = "distrito")
    private String district;

    @Column(name = "codsubpref")
    private String subPrefectureCode;

    @Column(name = "subpref")
    private String subPrefecture;

    @Column(name = "regiao5")
    private String region5;

    @Column(name = "regiao8")
    private String region8;

    @Column(name = "nome_feira")
    private String fairName;

    @Column(name = "registro", nullable = false, updatable = false)
    private String registerCode;

    @Column(name = "logradouro")
    private String address;

    @Column(name = "numero")
    private String number;

    @Column(name = "bairro")
    private String neighbourhood;

    @Column(name = "referencia")
    private String refPoint;

    @Builder.Default
    @Column(name = "ativo")
    private Boolean active = Boolean.TRUE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Fair fair = (Fair) o;
        return id != null && Objects.equals(id, fair.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    /**
     * Of fair.
     *
     * @param createDTO the create dto
     * @return the fair
     */
    public static Fair of(FairCreateDTO createDTO) {
        return Fair.builder()
                .lon(createDTO.getLon())
                .lat(createDTO.getLat())
                .censusSector(createDTO.getCensusSector())
                .wightedArea(createDTO.getWightedArea())
                .districtCode(createDTO.getDistrictCode())
                .district(createDTO.getDistrict())
                .subPrefectureCode(createDTO.getSubPrefectureCode())
                .subPrefecture(createDTO.getSubPrefecture())
                .region5(createDTO.getRegion5())
                .region8(createDTO.getRegion8())
                .fairName(createDTO.getFairName())
                .address(createDTO.getAddress())
                .number(createDTO.getNumber())
                .neighbourhood(createDTO.getNeighbourhood())
                .refPoint(createDTO.getRefPoint())
                .build();
    }
}
