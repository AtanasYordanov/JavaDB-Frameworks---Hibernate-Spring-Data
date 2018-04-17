package app.services.impl;

import app.constants.Messages;
import app.io.Writer;
import app.model.dtos.binding.CameraImportDto;
import app.model.entities.BasicCamera;
import app.repositories.CameraRepository;
import app.services.api.CameraService;
import app.utils.mapper.MapperConverter;
import app.utils.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CameraServiceImpl implements CameraService {

    private CameraRepository cameraRepository;
    private MapperConverter mapper;
    private Writer writer;

    @Autowired
    public CameraServiceImpl(CameraRepository cameraRepository, MapperConverter mapperConverter, Writer writer) {
        this.cameraRepository = cameraRepository;
        this.mapper = mapperConverter;
        this.writer = writer;
    }

    @Override
    public void save(BasicCamera camera) {
        this.cameraRepository.save(camera);
    }

    @Override
    public void saveAll(CameraImportDto[] cameras) {
        for (CameraImportDto cameraDto : cameras) {
            if (ValidationUtil.isValid(cameraDto)) {
                BasicCamera camera = this.mapper.convertCamera(cameraDto);
                this.save(camera);
                this.writer.println(Messages.SUCCESSFULLY_IMPORTED_CAMERA_MESSAGE, camera.getClass().getSimpleName(),
                        camera.getMake(), camera.getModel());
            }
        }
    }
}
