package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.model.dto.json.TownExportDtoJSON;
import org.softuni.mostwanted.model.dto.json.TownImportDtoJSON;

import java.util.List;

public interface TownService {
    void create(TownImportDtoJSON townDto);

    boolean contains(String name);

    List<TownExportDtoJSON> getAllRacingTowns();
}
