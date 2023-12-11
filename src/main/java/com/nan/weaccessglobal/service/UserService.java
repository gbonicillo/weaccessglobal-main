package com.nan.weaccessglobal.service;

import com.nan.weaccessglobal.dto.request.ChangePasswordRequest;
import com.nan.weaccessglobal.dto.request.RegisterRequest;

import java.security.Principal;

public interface UserService{

    public void changePassword(ChangePasswordRequest request, Principal connectedUser);

    public void updateProfile(RegisterRequest updateProfile);

    void getProfileDetails(Integer ambId);
}
