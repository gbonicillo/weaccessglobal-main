package com.nan.weaccessglobal.model.interfaces;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CommisionStatus {
    REQUESTED("Requested"),
    INPROGRESS("In Progress"),
    PAID("Paid"),
    NULL(null)
    ;

    @Getter
    private final String commission;
}
