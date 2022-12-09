package com.bochi.fairapi.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Fair partial dto.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FairPartialDTO {

    String field;

    String value;

}
