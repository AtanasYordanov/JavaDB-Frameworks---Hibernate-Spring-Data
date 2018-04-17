package app.services.api;

import app.model.dtos.binding.WorkshopImportDto;
import app.model.dtos.binding.WorkshopWrapperDto;
import app.model.dtos.view.WorkshopLocationWrapperView;
import app.model.entities.Workshop;

import java.util.List;

public interface WorkshopService {
    void save(Workshop workshop);

    void saveAll(List<WorkshopImportDto> workshops);

    WorkshopLocationWrapperView getWorkshopsByLocation();
}
