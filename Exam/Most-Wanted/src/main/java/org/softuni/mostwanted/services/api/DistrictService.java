package org.softuni.mostwanted.services.api;

import org.softuni.mostwanted.model.dto.json.DistrictImportDtoJSON;

public interface DistrictService {
    boolean contains(String name);

    void create(DistrictImportDtoJSON districtDto);
}
