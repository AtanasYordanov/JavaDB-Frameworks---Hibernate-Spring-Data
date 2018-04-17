package app.controllers;

import app.constants.Paths;
import app.model.dtos.binding.PhotographerImportDto;
import app.model.dtos.view.LandscapePhotographerView;
import app.model.dtos.view.PhotographerView;
import app.model.dtos.view.PhotographerWrapperView;
import app.services.api.PhotographerService;
import app.utils.serializers.api.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PhotographerController {

    private Serializer jsonSerializer;
    private Serializer xmlSerializer;
    private PhotographerService photographerService;

    @Autowired
    public PhotographerController(@Qualifier("json") Serializer jsonSerializer,
                                  @Qualifier("xml") Serializer xmlSerializer
            , PhotographerService photographerService) {
        this.jsonSerializer = jsonSerializer;
        this.xmlSerializer = xmlSerializer;
        this.photographerService = photographerService;
    }

    public void importPhotographers() {
        PhotographerImportDto[] photographers = this.jsonSerializer
                .deserialize(PhotographerImportDto[].class, Paths.PHOTOGRAPHERS_INPUT_PATH);
        this.photographerService.saveAll(photographers);
    }

    public void exportOrderedPhotographers() {
        List<PhotographerView> photographers = this.photographerService.getOrderedPhotographers();
        this.jsonSerializer.serialize(photographers, Paths.PHOTOGRAPHERS_ORDERED_JSON_OUTPUT_PATH);
    }

    public void exportLandscapePhotographers() {
        List<LandscapePhotographerView> photographers = this.photographerService.getLandscapePhotographers();
        this.jsonSerializer.serialize(photographers, Paths.PHOTOGRAPHERS_LANDSCAPE_OUTPUT_PATH);
    }

    public void exportSameCameraPhotographers() {
        PhotographerWrapperView photographers = this.photographerService.getSameCameraPhotographers();
        this.xmlSerializer.serialize(photographers, Paths.PHOTOGRAPHERS_SAME_CAMERA_OUTPUT_PATH);
    }
}
