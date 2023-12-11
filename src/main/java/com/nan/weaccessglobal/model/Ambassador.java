package com.nan.weaccessglobal.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ambassador")
public class Ambassador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ambassadorId;

    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthDate;
    private String contactNumber;
    private String abn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="amb_address_id")
    private AmbassadorAddress ambAddress;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;
}
