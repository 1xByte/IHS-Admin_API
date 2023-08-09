package com.adminapi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Builder
public class PlanDto {

    private Integer planId;

    private String planName;

    private LocalDate startDate;

    private LocalDate endDate;

    private String planCatagory;

}
