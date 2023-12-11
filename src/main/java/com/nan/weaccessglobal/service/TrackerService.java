package com.nan.weaccessglobal.service;

import com.nan.weaccessglobal.dto.request.ClientRequest;
import com.nan.weaccessglobal.dto.request.NoteRequest;
import com.nan.weaccessglobal.dto.response.NoteResponse;
import com.nan.weaccessglobal.dto.response.TrackLeadResponse;
import com.nan.weaccessglobal.model.Lead;
import com.nan.weaccessglobal.model.Notes;

import java.util.List;


public interface TrackerService {
    List<TrackLeadResponse> getAllMyLeads(Integer ambId);

    List<NoteResponse> getNotesOfSpecificLead(Integer id);

    Lead addLead(ClientRequest clientForm);

    Notes addNote(NoteRequest noteRequest);
}
