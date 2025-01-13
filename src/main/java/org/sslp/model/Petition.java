package org.sslp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Integer signs;
    private Boolean saved;
    private Boolean signed;

    @JsonProperty("petitioner_email")
    private String petitionerEmail;

    @JsonProperty("petitioner_name")
    private String petitionerName;

    @JsonProperty("petitioner_bioid")
    private String petitionerBioId;

    @JsonProperty("create_timestamp")
    private Timestamp createTimestamp;
}
