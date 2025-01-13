package org.sslp.controller;

import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.sslp.model.request.PetitionRequest;
import org.sslp.model.response.ApiResponse;
import org.sslp.model.response.FetchPetitionResponse;
import org.sslp.model.PetitionFetchParams;
import org.sslp.service.PetitionService;
import org.sslp.utils.JWTUtils;

import java.util.Collections;

@RestController
@RequestMapping("/petitions")
public class PetitionController {

    private final PetitionService petitionService;
    private final JWTUtils jwtUtils;

    PetitionController(PetitionService petitionService, JWTUtils jwtUtils) {
        this.petitionService = petitionService;
        this.jwtUtils = jwtUtils;
    }

    @GetMapping(value = {"/", ""})
    public ResponseEntity<? extends ApiResponse> getPetitions(
        @RequestParam(value = "page", required = false) Integer page,
        @RequestParam(value = "page-size", required = false) Integer pageSize,
        @RequestParam(value = "saved", required = false) String saved,
        @RequestParam(value = "signed", required = false) String signed,
        @RequestParam(value = "petitioner", required = false) String petitioner,
        @RequestHeader("access-token") String token
    ) {
        Claims claims = jwtUtils.isTokenValid(token);
        if(claims == null)
            return ApiResponse.error("invalid token").toResponseEntity(400);
        String userName = claims.get("username", String.class);
        return new FetchPetitionResponse(
            petitionService.getPetitions(new PetitionFetchParams(
                userName,
                page != null && pageSize != null ? (pageSize > 0 ? pageSize : null) : null,
                page != null && pageSize != null ? (page > 0 && pageSize > 0 ? (page - 1) * pageSize : null) : null,
                petitioner,
                saved != null ? userName : null,
                signed != null ? userName : null
            ))
        ).toResponseEntity();
    }

    @GetMapping("/{id}")
    public ResponseEntity<? extends ApiResponse> getPetitionById(
        @PathVariable("id") String id,
        @RequestHeader("access-token") String token
    ) {
        Claims claims = jwtUtils.isTokenValid(token);
        if(claims == null)
            return ApiResponse.error("invalid token").toResponseEntity(400);
        return new FetchPetitionResponse(
            Collections.singletonList(petitionService.getPetition(id, claims.get("username", String.class)))
        ).toResponseEntity();
    }

    @PostMapping(value = {"/", ""})
    public ResponseEntity<? extends ApiResponse> createPetition(
        @RequestHeader("access-token") String token,
        @RequestBody PetitionRequest petitionRequest
    ) {
        Claims claims = jwtUtils.isTokenValid(token);
        if(claims == null)
            return ApiResponse.error("invalid token").toResponseEntity(400);
        petitionService.createPetition(
            claims.get("username", String.class), petitionRequest.title(), petitionRequest.content()
        );
        return ApiResponse.success("petition created").toResponseEntity();
    }

    @PutMapping("/{id}")
    public ResponseEntity<? extends ApiResponse> updatePetition(
        @RequestHeader("access-token") String token,
        @PathVariable("id") String id,
        @RequestBody PetitionRequest petitionRequest
    ) {
        Claims claims = jwtUtils.isTokenValid(token);
        if(claims == null)
            return ApiResponse.error("invalid token").toResponseEntity(400);
        petitionService.updatePetitionContent(id, claims.get("username", String.class), petitionRequest.content());
        return ApiResponse.success("petition content updated").toResponseEntity();
    }

    @PostMapping("/sign/{id}")
    public ResponseEntity<? extends ApiResponse> signPetition(
        @RequestHeader("access-token") String token,
        @PathVariable("id") String id
    ) {
        Claims claims = jwtUtils.isTokenValid(token);
        if(claims == null)
            return ApiResponse.error("invalid token").toResponseEntity(400);
        petitionService.signPetition(id, claims.get("username", String.class));
        return ApiResponse.success("petition signed").toResponseEntity();
    }

    @PostMapping("/save/{id}")
    public ResponseEntity<? extends ApiResponse> savePetition(
        @RequestHeader("access-token") String token,
        @PathVariable("id") String id
    ) {
        Claims claims = jwtUtils.isTokenValid(token);
        if(claims == null)
            return ApiResponse.error("invalid token").toResponseEntity(400);
        petitionService.savePetition(id, claims.get("username", String.class));
        return ApiResponse.success("petition saved").toResponseEntity();
    }

    @DeleteMapping("/save/{id}")
    public ResponseEntity<? extends ApiResponse> unsavePetition(
        @RequestHeader("access-token") String token,
        @PathVariable("id") String id
    ) {
        Claims claims = jwtUtils.isTokenValid(token);
        if(claims == null)
            return ApiResponse.error("invalid token").toResponseEntity(400);
        petitionService.unsavePetition(id, claims.get("username", String.class));
        return ApiResponse.success("petition unsaved").toResponseEntity();
    }

    @PostMapping("/close/{id}")
    public ResponseEntity<? extends ApiResponse> closePetition(
        @RequestHeader("access-token") String token,
        @PathVariable("id") String id,
        @RequestBody PetitionRequest petitionRequest
    ) {
        Claims claims = jwtUtils.isTokenValid(token, true);
        if(claims == null)
            return ApiResponse.error("invalid token").toResponseEntity(400);
        petitionService.closePetitionWithResponse(id, petitionRequest.response());
        return ApiResponse.success("petition closed").toResponseEntity();
    }

}
