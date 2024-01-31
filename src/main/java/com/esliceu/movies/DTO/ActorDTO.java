package com.esliceu.movies.DTO;

import java.util.Map;

public record ActorDTO(String personName,String gender, String characterName) {
    @Override
    public String personName() {
        return personName;
    }

    @Override
    public String gender() {
        return gender;
    }

    @Override
    public String characterName() {
        return characterName;
    }
}
