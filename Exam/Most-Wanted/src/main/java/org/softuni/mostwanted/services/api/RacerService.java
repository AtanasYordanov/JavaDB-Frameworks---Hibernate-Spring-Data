package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.model.dto.json.RacerCarsExportDtoJSON;
import org.softuni.mostwanted.model.dto.json.RacerImportDtoJSON;

import java.util.List;

public interface RacerService {
    boolean contains(String name);

    void create(RacerImportDtoJSON racerDto);

    List<RacerCarsExportDtoJSON> getAllRacersWithCars();
}
