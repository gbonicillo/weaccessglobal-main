package com.nan.weaccessglobal.dto.request;

import com.nan.weaccessglobal.model.interfaces.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private String contactNumber;
    private String email;
    private String password;
    private Role role;
    private Integer abn;
    //Address
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String postCode;

}
