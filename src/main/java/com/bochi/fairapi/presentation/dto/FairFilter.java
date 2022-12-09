package com.bochi.fairapi.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Fair filter.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FairFilter {

    private String district;
    private String region5;
    private String fairName;
    private String neighbourhood;

}
