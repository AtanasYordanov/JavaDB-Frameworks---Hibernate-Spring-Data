package app.services.api;

import app.model.dtos.binding.LensImportDto;
import app.model.entities.Lens;

public interface LensService {
    void save(Lens lens);

    void saveAll(LensImportDto[] lenses);

    Lens findById(Long id);
}
