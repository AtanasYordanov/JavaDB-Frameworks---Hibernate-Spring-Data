package app.controllers;

import app.constants.Paths;
import app.model.dtos.binding.WorkshopWrapperDto;
import app.model.dtos.view.WorkshopLocationWrapperView;
import app.services.api.WorkshopService;
import app.utils.serializers.api.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class WorkshopController {

    private WorkshopService workshopService;
    private Serializer xmlSerializer;

    @Autowired
    public WorkshopController(WorkshopService workshopService, @Qualifier("xml") Serializer xmlSerializer) {
        this.workshopService = workshopService;
        this.xmlSerializer = xmlSerializer;
    }

    public void importWorkshops() {
        WorkshopWrapperDto workshops = this.xmlSerializer.deserialize(WorkshopWrapperDto.class
                , Paths.WORKSHOPS_INPUT_PATH);
        this.workshopService.saveAll(workshops.getWorkshops());
    }

    public void exportWorkshopsByLocation() {
        WorkshopLocationWrapperView locations = this.workshopService.getWorkshopsByLocation();
        this.xmlSerializer.serialize(locations, Paths.WORKSHOPS_BY_LOCATION_OUTPUT_PATH);
    }
}
