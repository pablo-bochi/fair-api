package com.bochi.fairapi.fair.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Fair update dto.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FairUpdateDTO {

    @JsonProperty("longitude")
    private String lon;

    @JsonProperty("latitude")
    private String lat;

    @JsonProperty("setor_censitario")
    private String censusSector;

    @JsonProperty("area_ponderada")
    private String wightedArea;

    @JsonProperty("cod_distrito")
    private String districtCode;

    @JsonProperty("distrito")
    private String district;

    @JsonProperty("cod_subprefeitura")
    private String subPrefectureCode;

    @JsonProperty("subprefeitura")
    private String subPrefecture;

    @JsonProperty("regiao_5")
    private String region5;

    @JsonProperty("regiao_8")
    private String region8;

    @JsonProperty("nome_feira")
    private String fairName;

    @JsonProperty("logradouro")
    private String address;

    @JsonProperty("numero")
    private String number;

    @JsonProperty("bairro")
    private String neighbourhood;

    @JsonProperty("referencia")
    private String refPoint;

}
