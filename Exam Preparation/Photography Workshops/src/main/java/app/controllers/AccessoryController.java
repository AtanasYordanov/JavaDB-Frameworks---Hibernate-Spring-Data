package app.controllers;

import app.constants.Paths;
import app.model.dtos.binding.AccessoryWrapperDto;
import app.services.api.AccessoryService;
import app.utils.serializers.XmlSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class AccessoryController {

    private XmlSerializer xmlSerializer;
    private AccessoryService accessoryService;

    @Autowired
    public AccessoryController(@Qualifier("xml") XmlSerializer xmlSerializer, AccessoryService accessoryService) {
        this.xmlSerializer = xmlSerializer;
        this.accessoryService = accessoryService;
    }


    public void importAccessories() {
        AccessoryWrapperDto accessories = this.xmlSerializer.deserialize(AccessoryWrapperDto.class
                , Paths.ACCESSORIES_INPUT_PATH);
        this.accessoryService.saveAll(accessories.getAccessories());
    }
}
