package com.nan.weaccessglobal.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrackLeadResponse {
    private Long leadId;
    private String firstName;
    private String lastName;
    private String nationality;
    private String emailAddress;
    private String contactNumber;
    private String leadStatus;
    private String leadStage;
    private String commissionStatus;
    private String service;
    private String counselorName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdated;
    //User Entity + Contact
    private String location;
    private String ambassadorName;
}
