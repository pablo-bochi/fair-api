package com.backend.fairapi


import com.backend.fairapi.domain.Fair
import com.backend.fairapi.presentation.dto.FairCreateDTO
import com.backend.fairapi.presentation.dto.FairFilter
import com.backend.fairapi.presentation.dto.FairResponseDTO
import com.backend.fairapi.presentation.dto.FairUpdateDTO

class Fixture {

    static Fair fair() {
        return Fair.builder()
                .lon("-46550164")
                .lat("-23558733")
                .censusSector("355030885000091")
                .wightedArea("3550308005040")
                .districtCode("87")
                .district("VILA FORMOSA")
                .subPrefectureCode("26")
                .subPrefecture("ARICANDUVA-FORMOSA-CARRAO")
                .region5("Leste")
                .region8("Leste 1")
                .fairName("VILA FORMOSA")
                .registerCode("4041-0")
                .address("RUA MARAGOJIPE")
                .number("S/N")
                .neighbourhood("VL FORMOSA")
                .refPoint("TV RUA PRETORIA")
                .build()
    }

    static Fair fairUpdated() {
        return Fair.builder()
                .lon("-46550164")
                .lat("-23558733")
                .censusSector("475030885000088")
                .wightedArea("4750308005088")
                .districtCode("35")
                .district("VILA PRUDENTE")
                .subPrefectureCode("15")
                .subPrefecture("PRUDENTE-FATIMA-PERUS")
                .region5("Oeste")
                .region8("Oeste 1")
                .fairName("VILA PRUDENTE")
                .address("RUA ACACIAS")
                .number("315")
                .neighbourhood("VL PRUDENTE")
                .refPoint("BABAU DO PARQUE")
                .registerCode("4041-0")
                .build()
    }

    static Fair fairPartiallyUpdated() {
        return Fair.builder()
                .lon("-46550164")
                .lat("-23558733")
                .censusSector("355030885000091")
                .wightedArea("3550308005040")
                .districtCode("87")
                .district("VILA FORMOSA")
                .subPrefectureCode("26")
                .subPrefecture("ARICANDUVA-FORMOSA-CARRAO")
                .region5("Leste")
                .region8("Leste 1")
                .fairName("VILA FORMOSA")
                .registerCode("4041-0")
                .address("RUA MARAGOJIPE")
                .number("S/N")
                .neighbourhood("VL PRUDENTE")
                .refPoint("TV RUA PRETORIA")
                .build()
    }

    static FairCreateDTO createDTO() {
        return FairCreateDTO.builder()
                .lon("-46550164")
                .lat("-23558733")
                .censusSector("355030885000091")
                .wightedArea("3550308005040")
                .districtCode("87")
                .district("VILA FORMOSA")
                .subPrefectureCode("26")
                .subPrefecture("ARICANDUVA-FORMOSA-CARRAO")
                .region5("Leste")
                .region8("Leste 1")
                .fairName("VILA FORMOSA")
                .registerCode("4041-0")
                .address("RUA MARAGOJIPE")
                .number("S/N")
                .neighbourhood("VL FORMOSA")
                .refPoint("TV RUA PRETORIA")
                .build()
    }

    static FairFilter fairFilter() {
        return FairFilter.builder()
                .district("VILA FORMOSA")
                .region5("Leste")
                .fairName("Leste 1")
                .neighbourhood("VL FORMOSA")
                .build()
    }

    static com.backend.fairapi.presentation.dto.FairPartialDTO partialDTO() {
        return com.backend.fairapi.presentation.dto.FairPartialDTO.builder()
                .field("bairro")
                .value("VL PRUDENTE")
                .build()
    }

    static FairResponseDTO responseDTO() {
        return FairResponseDTO.of(fair())
    }

    static FairUpdateDTO updateDTO() {
        return FairUpdateDTO.builder()
                .lon("-46550164")
                .lat("-23558733")
                .censusSector("475030885000088")
                .wightedArea("4750308005088")
                .districtCode("35")
                .district("VILA PRUDENTE")
                .subPrefectureCode("15")
                .subPrefecture("PRUDENTE-FATIMA-PERUS")
                .region5("Oeste")
                .region8("Oeste 1")
                .fairName("VILA PRUDENTE")
                .address("RUA ACACIAS")
                .number("315")
                .neighbourhood("VL PRUDENTE")
                .refPoint("BABAU DO PARQUE")
                .build()
    }

}
