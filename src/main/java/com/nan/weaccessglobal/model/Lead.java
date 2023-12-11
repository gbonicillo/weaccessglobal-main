package com.nan.weaccessglobal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nan.weaccessglobal.model.interfaces.CommisionStatus;
import com.nan.weaccessglobal.model.interfaces.LeadStage;
import com.nan.weaccessglobal.model.interfaces.LeadStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="leads")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lead_id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long leadId;
    private String firstName;
    private String lastName;
    private String nationality;
    private String emailAddress;
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    private LeadStatus leadStatus;

    @Enumerated(EnumType.STRING)
    private LeadStage leadStage;

    @Enumerated(EnumType.STRING)
    private CommisionStatus commissionStatus;

    private String service;

    private String counselorName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private LocalDateTime lastUpdated;

    //User Entity + Contact
    private String location;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ambassador_id")
    public Ambassador ambassador;

}
