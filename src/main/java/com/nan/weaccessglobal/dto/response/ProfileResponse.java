package com.nan.weaccessglobal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
    private String firstName;
    private String lastName;
    private String nationality;
    private String email;
    private String contactNumber;
    private String leadStatus;
    private String leadStage;
    private String commissionStatus;
    private String service;
    private String counselorName;
    private String location;
    private Integer ambassadorId;
}
