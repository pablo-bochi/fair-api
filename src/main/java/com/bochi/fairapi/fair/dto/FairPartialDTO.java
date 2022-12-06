package com.bochi.fairapi.fair.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * The type Fair partial dto.
 */
@Data
@Builder
@AllArgsConstructor
public class FairPartialDTO {

    String field;

    String value;

}
