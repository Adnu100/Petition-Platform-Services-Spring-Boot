package org.sslp.model.response;

import lombok.Getter;
import org.sslp.model.Petition;

import java.util.List;

@Getter
public class FetchPetitionResponse extends ApiResponse {

    private final List<Petition> data;

    public FetchPetitionResponse(List<Petition> petitions) {
        super(SUCCESS, null);
        this.data = petitions;
    }

}
