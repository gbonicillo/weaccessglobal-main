package com.nan.weaccessglobal.controller;

import com.nan.weaccessglobal.dto.request.ChangePasswordRequest;
import com.nan.weaccessglobal.dto.request.RegisterRequest;
import com.nan.weaccessglobal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PatchMapping
    public ResponseEntity<?> changePassword(
            @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/profile")
    @PreAuthorize("hasAuthority('admin:update')")
    public ResponseEntity<?> updateProfile(@ModelAttribute RegisterRequest updateProfile) {
        try{
            service.updateProfile(updateProfile);
            return ResponseEntity.ok().build();
        }catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }
    @GetMapping("/profile/{ambId}")
    public ResponseEntity<?> getProfileDetails(@PathVariable Integer ambId) {
        try{
            service.getProfileDetails(ambId);
            return ResponseEntity.ok().build();
        }catch(Exception e) {
            return ResponseEntity.internalServerError().build();
        }

    }
}
