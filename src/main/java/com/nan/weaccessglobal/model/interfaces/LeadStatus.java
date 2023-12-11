package com.nan.weaccessglobal.model.interfaces;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LeadStatus {
    NEW("New"),
    PENDING("Pending"),
    COMPLETED("Completed")

    ;
    @Getter
    private final String status;
}
