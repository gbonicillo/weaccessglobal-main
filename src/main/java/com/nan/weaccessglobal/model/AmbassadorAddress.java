package com.nan.weaccessglobal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ambassador_address")
public class AmbassadorAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amb_address_id", nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long ambAddressId;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String postCode;

}
