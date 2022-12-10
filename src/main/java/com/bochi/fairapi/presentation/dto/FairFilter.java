package com.bochi.fairapi.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The filter used to search a page of Fairs.
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
