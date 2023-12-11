package com.nan.weaccessglobal.model.interfaces;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LeadStage {

    PROFILE_RECEIVED("Profile Received"),
    CONSULTATION_FINISHED("Consultation Finished"),
    UNRESPONSIVE("Unresponsive"),
    REQUIREMENTS_RECEIVED("Requirements Received"),
    DISCONTINUED("Discontinued"),
    LODGED("Lodged"),
    APPROVED("Approved")

    ;

    @Getter
    private final String stage;
}
