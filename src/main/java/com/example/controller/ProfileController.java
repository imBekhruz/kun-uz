package com.example.controller;

import com.example.dto.ProfileDTO;
import com.example.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody ProfileDTO profileDTO){
        ProfileDTO profile = profileService.createProfile(profileDTO);
        return ResponseEntity.ok(profile);
    }

    @GetMapping("")
    public ResponseEntity<?> getList(){
        List<ProfileDTO> profileList = profileService.getProfileList();
        return ResponseEntity.ok(profileList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable Integer id){
        ProfileDTO profileDTO = profileService.getProfileById(id);
        return ResponseEntity.ok(profileDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Integer id){
        Boolean result = profileService.deleteById(id);
        return ResponseEntity.ok(result);
    }
}
