package com.bochi.fairapi.fair.dto;

import com.bochi.fairapi.fair.Fair;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * The type Fair response dto.
 */
@Data
@Builder
@AllArgsConstructor
public class FairResponseDTO {

    @JsonProperty("long")
    private String lon;

    @JsonProperty("lat")
    private String lat;

    @JsonProperty("setcens")
    private String censusSector;

    @JsonProperty("areap")
    private String wightedArea;

    @JsonProperty("coddist")
    private String districtCode;

    @JsonProperty("distrito")
    private String district;

    @JsonProperty("codsubpref")
    private String subPrefectureCode;

    @JsonProperty("subpref")
    private String subPrefecture;

    @JsonProperty("regiao5")
    private String region5;

    @JsonProperty("regiao8")
    private String region8;

    @JsonProperty("nome_feira")
    private String fairName;

    @JsonProperty("registro")
    private String registerCode;

    @JsonProperty("logradouro")
    private String address;

    @JsonProperty("numero")
    private String number;

    @JsonProperty("bairro")
    private String neighbourhood;

    @JsonProperty("referencia")
    private String refPoint;

    @JsonProperty("ativo")
    private Boolean active;

    /**
     * Of fair response dto.
     *
     * @param fair the fair
     * @return the fair response dto
     */
    public static FairResponseDTO of(Fair fair) {
        return FairResponseDTO.builder()
                .lon(fair.getLon())
                .lat(fair.getLat())
                .censusSector(fair.getCensusSector())
                .wightedArea(fair.getWightedArea())
                .districtCode(fair.getDistrictCode())
                .district(fair.getDistrict())
                .subPrefectureCode(fair.getSubPrefectureCode())
                .subPrefecture(fair.getSubPrefecture())
                .region5(fair.getRegion5())
                .region8(fair.getRegion8())
                .fairName(fair.getFairName())
                .address(fair.getAddress())
                .number(fair.getNumber())
                .neighbourhood(fair.getNeighbourhood())
                .refPoint(fair.getRefPoint())
                .build();
    }

}
