package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.model.dto.xml.RaceImportDtoXML;

public interface RaceService {
    long getLastId();

    void create(RaceImportDtoXML raceDto);
}
