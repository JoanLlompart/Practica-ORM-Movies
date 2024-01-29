package com.esliceu.movies.Services;

import com.esliceu.movies.Repos.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PersonServices {
    @Autowired
    MovieCrewServices movieCrewServices;

    @Autowired
    PersonRepo personRepo;
    public String deletePerson(Map<String, String> data) {
        Long personId = Long.valueOf(data.get("value1"));
        if (personRepo.existsByPersonId(personId)) {
            //todo:probant de borrar correctament
            movieCrewServices.deleteByPersonId(personId);
            personRepo.deleteById(personId);
            return "Person by id ," + personId + " ,Delete successfully";
        } else {
            return "Person Delete Error";
        }
    }
}
