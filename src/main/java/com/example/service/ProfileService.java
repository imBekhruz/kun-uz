package com.example.service;

import com.example.dto.ProfileDTO;
import com.example.exp.ItemNotFoundException;
import com.example.ntt.ProfileEntity;
import com.example.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;


    public ProfileDTO createProfile(ProfileDTO profileDTO) {
        ProfileEntity profileEntity = toEntity(profileDTO);

        profileRepository.save(profileEntity);
        profileDTO.setId(profileEntity.getId());
        return profileDTO;
    }

    public List<ProfileDTO> getProfileList() {
        Iterable<ProfileEntity> profileEntityList = profileRepository.findAll();

        List<ProfileDTO> profileDTOS = new ArrayList<>();

        for (ProfileEntity profileEntity : profileEntityList) {
            ProfileDTO profileDTO = toDTO(profileEntity);
            profileDTOS.add(profileDTO);
        }

        return profileDTOS;
    }

    public ProfileDTO getProfileById(Integer id) {
        ProfileEntity profileEntity = profileRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Item not found");
        });
        return toDTO(profileEntity);
    }

    public Boolean deleteById(Integer id) {
        get(id);
        profileRepository.deleteById(id);
        return true;
    }

    public Boolean updateProfile(ProfileDTO profileDTO, Integer id) {

        ProfileEntity profileEntity = get(id);
        profileEntity.setName(profileDTO.getName());
        profileEntity.setSurname(profileDTO.getSurname());
        return true;
    }


    public ProfileEntity toEntity(ProfileDTO profileDTO) {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setName(profileDTO.getName());
        profileEntity.setSurname(profileDTO.getSurname());

        return profileEntity;
    }

    public ProfileDTO toDTO(ProfileEntity profileEntity) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(profileEntity.getId());
        profileDTO.setName(profileEntity.getName());
        profileDTO.setSurname(profileEntity.getSurname());

        return profileDTO;
    }

    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Item not found");
        });
    }
}
