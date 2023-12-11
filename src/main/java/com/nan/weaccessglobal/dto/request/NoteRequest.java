package com.nan.weaccessglobal.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteRequest {
    private String subject;
    private String note;
    private String ambassadorName;
    private String counselorName;
    private Long leadId;
}
