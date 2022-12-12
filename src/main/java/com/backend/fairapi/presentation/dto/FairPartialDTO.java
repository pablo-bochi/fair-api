package com.backend.fairapi.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The DTO to update a single field of a Fair
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FairPartialDTO {

    String field;

    String value;

}
