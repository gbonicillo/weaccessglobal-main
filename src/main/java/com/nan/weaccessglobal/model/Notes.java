package com.nan.weaccessglobal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name="notes")
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long noteId;

    private String subject;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;

    private String ambassadorName;
    private String counselorName;
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lead_note_id")
    private Lead lead;
}
