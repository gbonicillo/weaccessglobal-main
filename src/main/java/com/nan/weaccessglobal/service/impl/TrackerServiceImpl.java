package com.nan.weaccessglobal.service.impl;

import com.nan.weaccessglobal.dto.request.ClientRequest;
import com.nan.weaccessglobal.dto.request.NoteRequest;
import com.nan.weaccessglobal.dto.response.NoteResponse;
import com.nan.weaccessglobal.dto.response.TrackLeadResponse;
import com.nan.weaccessglobal.model.Ambassador;
import com.nan.weaccessglobal.model.Lead;
import com.nan.weaccessglobal.model.Notes;
import com.nan.weaccessglobal.model.interfaces.LeadStage;
import com.nan.weaccessglobal.model.interfaces.LeadStatus;
import com.nan.weaccessglobal.repository.AmbassadorRepository;
import com.nan.weaccessglobal.repository.NoteRepository;
import com.nan.weaccessglobal.repository.TrackerRepository;
import com.nan.weaccessglobal.repository.UserRepository;
import com.nan.weaccessglobal.service.TrackerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrackerServiceImpl implements TrackerService {

    private final TrackerRepository trackerRepository;
    private final UserRepository userRepository;

    private final NoteRepository noteRepository;
    private final AmbassadorRepository ambassadorRepository;

    @Override
    public List<TrackLeadResponse> getAllMyLeads(Integer ambId) {
        List<Lead> leadList = new ArrayList<>();
        Optional<Ambassador> ambassador = ambassadorRepository.findById(ambId);
        if(ambassador.isPresent()){
            leadList = trackerRepository.findByAmbassador(ambassador.get().getAmbassadorId());
        }
        //list count
        //return new PageResult<>(lead,leadListCount, MapUtils.getInteger(mapParam,"limit"));
        return leadList.stream().map(this::mapToTrackLeadResponseModel).collect(Collectors.toList());
    }

    @Override
    public List<NoteResponse> getNotesOfSpecificLead(Integer id) {
        List<Notes> notes = noteRepository.findAllById(Collections.singleton(id));
        return notes.stream().map(this::mapToNoteResponseModel).collect(Collectors.toList());
    }

    @Override
    public Lead addLead(ClientRequest clientForm) {
        var ambassador = ambassadorRepository.findById(clientForm.getAmbassadorId());
        var lead = Lead.builder()
                .leadStatus(LeadStatus.NEW)
                .leadStage(LeadStage.PROFILE_RECEIVED)
                .commissionStatus(null)
                .firstName(clientForm.getFirstName())
                .lastName(clientForm.getLastName())
                .nationality(clientForm.getNationality())
                .emailAddress(clientForm.getEmail())
                .contactNumber(clientForm.getContactNumber())
                .counselorName(clientForm.getCounselorName())
                .location(clientForm.getLocation())
                .service(clientForm.getService())
                .lastUpdated(LocalDateTime.now())
                .ambassador(ambassador.orElse(null))
                .build();
        return trackerRepository.saveAndFlush(lead);
    }

    @Override
    public Notes addNote(NoteRequest noteRequest) {
        Notes note = new Notes();

        return noteRepository.saveAndFlush(note);
    }

    private NoteResponse mapToNoteResponseModel(Notes notes) {

        return NoteResponse.builder()
                .note(notes.getNote())
                .noteId(notes.getNoteId())
                .ambassadorName(notes.getAmbassadorName())
                .counselorName(notes.getCounselorName())
                .createdDate(notes.getCreatedDate())
                .subject(notes.getSubject())
                .build();
    }

    private TrackLeadResponse mapToTrackLeadResponseModel(Lead lead){
        /*DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss.zzz");
        LocalDate dt = ,
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));*/
        String ambassadorName = StringUtils.join(lead.getAmbassador().getUser().getFirstname()," ", lead.getAmbassador().getUser().getLastname());
        return TrackLeadResponse.builder()
                .leadId(lead.getLeadId())
                .leadStatus(lead.getLeadStatus().getStatus())
                .leadStage(lead.getLeadStage().getStage())
                .commissionStatus(StringUtils.defaultIfEmpty(lead.getCommissionStatus().getCommission(),""))
                .firstName(lead.getFirstName())
                .lastName(lead.getLastName())
                .nationality(lead.getNationality())
                .emailAddress(lead.getEmailAddress())
                .contactNumber(lead.getContactNumber())
                .ambassadorName(ambassadorName)
                .counselorName(lead.getCounselorName())
                .location(lead.getLocation())
                .service(lead.getService())
                .lastUpdated(lead.getLastUpdated())
                .build();
    }
}
