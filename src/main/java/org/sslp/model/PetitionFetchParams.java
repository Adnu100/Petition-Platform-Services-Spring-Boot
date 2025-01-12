package org.sslp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PetitionFetchParams {
    Integer pageNumber;
    Integer pageSize;
    String petitioner;
    String savedBy;
    String signedBy;
}
