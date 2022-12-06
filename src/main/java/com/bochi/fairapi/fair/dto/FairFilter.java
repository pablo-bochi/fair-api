package com.bochi.fairapi.fair.dto;

import lombok.Builder;
import lombok.Data;

/**
 * The type Fair filter.
 */
@Data
@Builder
public class FairFilter {

    private String district;
    private String region5;
    private String fairName;
    private String neighbourhood;

}
