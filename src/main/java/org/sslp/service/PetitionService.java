package org.sslp.service;

import org.springframework.stereotype.Service;
import org.sslp.model.Petition;
import org.sslp.model.PetitionFetchParams;

import java.util.Collections;
import java.util.List;

@Service
public class PetitionService {

    public List<Petition> getPetitions(PetitionFetchParams params) {
        return Collections.emptyList();
    }

    public Petition getPetition(String id, String petitioner) {
        return null;
    }

    public void createPetition(String petitioner, String title, String content) {

    }

    public void updatePetitionContent(String id, String petitioner, String content) {

    }

    public void signPetition(String id, String petitioner) {

    }

    public void savePetition(String id, String petitioner) {

    }

    public void unsavePetition(String id, String petitioner) {

    }

    public void closePetitionWithResponse(String id, String response) {

    }

}
