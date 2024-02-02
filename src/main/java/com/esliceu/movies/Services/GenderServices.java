package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.Gender;
import com.esliceu.movies.Repos.GenderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GenderServices {
    @Autowired
    GenderRepo genderRepo;

    public List<Gender> getAllGender() {
        return genderRepo.findAll();
    }
}
