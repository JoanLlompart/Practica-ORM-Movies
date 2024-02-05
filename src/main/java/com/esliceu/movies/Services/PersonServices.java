package com.esliceu.movies.Services;

import com.esliceu.movies.Entities.Person;
import com.esliceu.movies.Repos.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PersonServices {
    @Autowired
    MovieCrewServices movieCrewServices;
    @Autowired
    MovieCastServices movieCastServices;

    @Autowired
    PersonRepo personRepo;
    public String deletePerson(Map<String, String> data) {
        Long personId = Long.valueOf(data.get("value1"));
        if (personRepo.existsByPersonId(personId)) {
            movieCrewServices.deleteByPersonId(personId);
            movieCastServices.deleteByPersonId(personId);
            personRepo.deleteById(personId);
            return "Person by id ," + personId + " ,Delete successfully";
        } else {
            return "Person Delete Error";
        }
    }

    public List<Person> findPersonsByKeyword(Map<String, String> formData) {
        /*
        keyword: personKeyword,
        personPage: personPage,
        personSize: personSize
         */
        String keyword = formData.get("keyword");
        int page = Integer.parseInt(formData.get("personPage"));
        int size = Integer.parseInt(formData.get("personSize"));

        Pageable pageable = PageRequest.of(page,size);
        return personRepo.findByPersonNameContaining(keyword,pageable);
    }

    public Person findPersonById(Long personId) {
        return personRepo.getReferenceById(personId);
    }
}
