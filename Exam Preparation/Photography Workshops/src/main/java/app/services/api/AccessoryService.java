package app.services.api;

import app.model.dtos.binding.AccessoryImportDto;
import app.model.entities.Accessory;

import java.util.List;

public interface AccessoryService {
    void save(Accessory accessory);

    void saveAll(List<AccessoryImportDto> accessories);
}
