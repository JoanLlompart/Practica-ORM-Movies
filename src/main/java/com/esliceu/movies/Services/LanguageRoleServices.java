package com.esliceu.movies.Services;
import com.esliceu.movies.Entities.LanguageRole;
import com.esliceu.movies.Repos.LanguageRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LanguageRoleServices {
    @Autowired
    LanguageRoleRepo languageRoleRepo;
    public String deleteLanguageRole(Map<String, String> data) {
        Long roleId = Long.valueOf(data.get("value1"));
        //Long roleId = 5L;
        if (languageRoleRepo.existsByRoleId(roleId)) {
            languageRoleRepo.deleteById(roleId);
            return "LanguageRole by id ," + roleId + " ,Delete successfully";
        } else {
            return "Language Role Delete Error";
        }
    }

    public List<LanguageRole> filterByLanguageRole(String keyword, Pageable pageable) {
        return languageRoleRepo.findByLanguageRoleContainingIgnoreCase(keyword,pageable);
    }

}
