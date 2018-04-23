package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.model.dto.json.TownImportDtoJSON;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.TownService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class TownController {

    private TownService townService;
    private Parser jsonParser;

    public TownController(TownService townService, @Qualifier("JSONParser") Parser jsonParser) {
        this.townService = townService;
        this.jsonParser = jsonParser;
    }

    public String importTownsFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            TownImportDtoJSON[] townDtos = this.jsonParser.read(TownImportDtoJSON[].class, jsonContent);
            for (TownImportDtoJSON townDto : townDtos) {
                if (ValidationUtil.isValid(townDto)) {
                    if (this.townService.contains(townDto.getName())) {
                        sb.append("Error: Duplicate data.");
                    } else {
                        this.townService.create(townDto);
                        sb.append(String.format("Successfully created Town - %s.", townDto.getName()));
                    }
                } else {
                    sb.append("Error: Incorrect data.");
                }
                sb.append(System.lineSeparator());
            }
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            sb.append("Error: Invalid data.").append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String exportRacingTowns() {
        try {
            return this.jsonParser.write(this.townService.getAllRacingTowns());
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
