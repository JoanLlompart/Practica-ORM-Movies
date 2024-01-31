package com.esliceu.movies.DTO;

import java.util.Map;

public record ActorDTO(String personName, String characterName,String title) {
    public ActorDTO(String personName, String characterName, String title) {
        this.personName = personName;
        this.characterName = characterName;
        this.title = title;
    }
}
