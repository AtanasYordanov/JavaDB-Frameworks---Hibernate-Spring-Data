package app.controllers;

import app.constants.Paths;
import app.model.dtos.binding.LensImportDto;
import app.services.api.LensService;
import app.utils.serializers.api.Serializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class LensController {

    private Serializer jsonSerializer;
    private LensService lensService;

    @Autowired
    public LensController(@Qualifier("json") Serializer jsonSerializer, LensService lensService) {
        this.lensService = lensService;
        this.jsonSerializer = jsonSerializer;
    }

    public void importLenses() {
        LensImportDto[] lenses = this.jsonSerializer.deserialize(LensImportDto[].class, Paths.LENSES_INPUT_PATH);
        this.lensService.saveAll(lenses);
    }
}
