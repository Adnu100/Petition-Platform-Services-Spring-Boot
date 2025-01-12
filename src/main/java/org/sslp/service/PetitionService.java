package org.sslp.service;

import org.springframework.stereotype.Service;
import org.sslp.dao.PetitionDao;
import org.sslp.model.Petition;
import org.sslp.model.PetitionFetchParams;

import java.util.List;

@Service
public class PetitionService {

    private final PetitionDao petitionDao;

    public PetitionService(PetitionDao petitionDao) {
        this.petitionDao = petitionDao;
    }

    public List<Petition> getPetitions(PetitionFetchParams params) {
        boolean pagination = params.getPageNumber() != null && params.getPageSize() != null;
        if(params.getSavedBy() != null)
            return pagination ?
                petitionDao.findSaved(params.getSavedBy(), params.getPageNumber(), params.getPageSize()) :
                petitionDao.findSaved(params.getSavedBy());
        if(params.getSignedBy() != null)
            return pagination ?
                petitionDao.findSigned(params.getSignedBy(), params.getPageNumber(), params.getPageSize()) :
                petitionDao.findSigned(params.getSignedBy());
        if(params.getPetitioner() != null)
            return pagination ?
                petitionDao.findByPetitioner(
                    params.getPetitioner(), params.getPetitioner(), params.getPageNumber(), params.getPageSize()
                ) :
                petitionDao.findByPetitioner(params.getPetitioner(), params.getPetitioner());
        return pagination ?
            petitionDao.findAll(params.getUserName(), params.getPageNumber(), params.getPageSize()) :
            petitionDao.findAll(params.getUserName());
    }

    public Petition getPetition(String id, String petitioner) {
        return petitionDao.findById(id, petitioner);
    }

    public void createPetition(String petitioner, String title, String content) {
        petitionDao.createPetition(petitioner, title, content);
    }

    public void updatePetitionContent(String id, String petitioner, String content) {
        petitionDao.updatePetitionContent(id, petitioner, content);
    }

    public void signPetition(String id, String petitioner) {
        petitionDao.signPetition(id, petitioner);
    }

    public void savePetition(String id, String petitioner) {
        petitionDao.savePetition(id, petitioner);
    }

    public void unsavePetition(String id, String petitioner) {
        petitionDao.unsavePetition(id, petitioner);
    }

    public void closePetitionWithResponse(String id, String response) {
        petitionDao.closePetitionWithResponse(id, response);
    }

}
