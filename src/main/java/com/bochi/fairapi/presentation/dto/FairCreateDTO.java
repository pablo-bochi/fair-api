package com.bochi.fairapi.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The DTO to create a new Fair
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FairCreateDTO {

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

    @JsonProperty("subprefe")
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

}
