package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.model.dto.xml.MostWantedExportWrapperDtoXML;
import org.softuni.mostwanted.model.dto.xml.RaceEntryImportDtoXML;

public interface RaceEntryService {
    Long getLastId();

    void create(RaceEntryImportDtoXML raceEntryDto);

    MostWantedExportWrapperDtoXML getMostWantedRacer();
}
