package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.model.dto.json.CarImportDtoJSON;

public interface CarService {
    void create(CarImportDtoJSON carDto);
}
