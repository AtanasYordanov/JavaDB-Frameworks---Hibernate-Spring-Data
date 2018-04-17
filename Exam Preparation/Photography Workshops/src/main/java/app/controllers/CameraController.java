package app.controllers;

import app.constants.Paths;
import app.model.dtos.binding.CameraImportDto;
import app.services.api.CameraService;
import app.utils.serializers.api.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;

@Controller
public class CameraController {

    private Serializer jsonSerializer;
    private CameraService cameraService;

    @Autowired
    public CameraController(@Qualifier("json") Serializer jsonSerializer, CameraService cameraService) {
        this.jsonSerializer = jsonSerializer;
        this.cameraService = cameraService;
    }

    public void importCameras() throws JAXBException {
            CameraImportDto[] cameras = this.jsonSerializer.deserialize(CameraImportDto[].class
                    , Paths.CAMERAS_INPUT_PATH);
            this.cameraService.saveAll(cameras);
    }
}
