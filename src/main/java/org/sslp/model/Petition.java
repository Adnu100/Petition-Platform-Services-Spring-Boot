package org.sslp.model;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Builder
@Data
public class Petition {
    private String id;
    private String title;
    private String content;
    private String status;
    private String response;
    private String petitionerEmail;
    private String petitionerName;
    private String petitionerBioId;
    private Timestamp createTimestamp;
    private Integer signs;
    private Boolean saved;
    private Boolean signed;
}
