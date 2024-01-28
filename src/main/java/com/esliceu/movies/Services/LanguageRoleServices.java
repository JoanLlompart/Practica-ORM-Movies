package com.esliceu.movies.Services;
import com.esliceu.movies.DTO.LanguageRoleDTO;
import com.esliceu.movies.Entities.LanguageRole;
import com.esliceu.movies.Repos.LanguageRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LanguageRoleServices {
    @Autowired
    LanguageRoleRepo languageRoleRepo;

    public String deleteLanguageRole(Map<String, String> data) {
        Long roleId = Long.valueOf(data.get("value1"));
        if (languageRoleRepo.existsByRoleId(roleId)) {
            languageRoleRepo.deleteById(roleId);
            return "LanguageRole by id ," + roleId + " ,Delete successfully";
        } else {
            return "Language Role Delete Error";
        }
    }

    public List<LanguageRoleDTO> filterByLanguageRole(String keyword, Pageable pageable) {
       List<LanguageRole> languageRoles = languageRoleRepo.findByLanguageRoleContainingIgnoreCase(keyword,pageable);
        List<LanguageRoleDTO> roleDTOList = new ArrayList<>();
        //Converteix el la llista de languageRoles a una llisat DTO de roleDTOList.
        // Per evitar que aparesqui a la llista de SET de movieLanguages
       for (LanguageRole lr:languageRoles) {
           LanguageRoleDTO lrDTO = new LanguageRoleDTO(lr.getRoleId(),lr.getLanguageRole());
          roleDTOList.add(lrDTO);
        }
       return roleDTOList;
    }

}
