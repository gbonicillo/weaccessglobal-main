package com.nan.weaccessglobal.controller;

import com.nan.weaccessglobal.dto.request.ClientRequest;
import com.nan.weaccessglobal.dto.request.NoteRequest;
import com.nan.weaccessglobal.dto.response.NoteResponse;
import com.nan.weaccessglobal.dto.response.TrackLeadResponse;
import com.nan.weaccessglobal.model.Lead;
import com.nan.weaccessglobal.model.Notes;
import com.nan.weaccessglobal.service.TrackerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tracker")
@RequiredArgsConstructor
public class TrackerController {

    private final TrackerService trackerService;

    @GetMapping("/leads/{ambId}")
    public ResponseEntity<List<TrackLeadResponse>> getMyLeads(@PathVariable Integer ambId) {
        return ResponseEntity.ok(trackerService.getAllMyLeads(ambId));
    }

    @PostMapping("/leads")
    public ResponseEntity<Lead> addLead(@ModelAttribute ClientRequest clientForm){
        Lead createdLead = trackerService.addLead(clientForm);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdLead.getLeadId())
                .toUri();
        return ResponseEntity.created(location).body(createdLead);
    }

    @GetMapping("/notes/{id}")
    public List<NoteResponse> getNotesOfLead(@PathVariable Integer id){
        return trackerService.getNotesOfSpecificLead(id);
    }

    @PostMapping("/notes")
    public ResponseEntity<Notes> addNote(NoteRequest noteRequest){
        Notes createdNote = trackerService.addNote(noteRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdNote.getNoteId())
                .toUri();
        return ResponseEntity.created(location).body(createdNote);
    }
}
