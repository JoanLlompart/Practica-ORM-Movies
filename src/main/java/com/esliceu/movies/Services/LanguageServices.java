package com.esliceu.movies.Services;

import com.esliceu.movies.Repos.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LanguageServices {
    @Autowired
    LanguageRepo languageRepo;
    @Autowired
    AdminAddServices adminAddServices;

    public String deleteLanguage(Map<String, String> data) {
        Long languageId = Long.valueOf(data.get("value1"));
        //Long languageId = 24705L;
        if (languageRepo.existsByLanguageId(languageId)) {;
            languageRepo.deleteById(languageId);
            return "Language by id ," + languageId + " ,Delete successfully";
        } else {
            return "Language Delete Error";
        }
    }
}
