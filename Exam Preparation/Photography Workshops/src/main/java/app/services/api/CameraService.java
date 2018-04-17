package app.services.api;

import app.model.dtos.binding.CameraImportDto;
import app.model.entities.BasicCamera;

import java.util.List;

public interface CameraService {
    void save(BasicCamera camera);

    void saveAll(CameraImportDto[] cameras);
}
