package app.services.api;

import app.model.dtos.binding.PhotographerImportDto;
import app.model.dtos.view.LandscapePhotographerView;
import app.model.dtos.view.PhotographerView;
import app.model.dtos.view.PhotographerWrapperView;
import app.model.entities.Photographer;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhotographerService {
    void save(Photographer photographer);

    void saveAll(PhotographerImportDto[] photographers);

    List<PhotographerView> getOrderedPhotographers();

    List<LandscapePhotographerView> getLandscapePhotographers();

    PhotographerWrapperView getSameCameraPhotographers();
}
