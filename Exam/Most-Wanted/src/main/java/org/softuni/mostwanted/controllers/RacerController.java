package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.model.dto.json.RacerImportDtoJSON;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RacerController {

    private RacerService racerService;
    private Parser jsonParser;

    @Autowired
    public RacerController(RacerService racerService, @Qualifier("JSONParser") Parser jsonParser) {
        this.racerService = racerService;
        this.jsonParser = jsonParser;
    }

    public String importRacersFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            RacerImportDtoJSON[] racerDtos = this.jsonParser.read(RacerImportDtoJSON[].class, jsonContent);
            for (RacerImportDtoJSON racerDto : racerDtos) {
                if (ValidationUtil.isValid(racerDto)) {
                    if (this.racerService.contains(racerDto.getName())) {
                        sb.append("Error: Duplicate data.");
                    } else {
                        try {
                            this.racerService.create(racerDto);
                            sb.append(String.format("Successfully created Racer - %s.", racerDto.getName()));
                        } catch (IllegalArgumentException e) {
                            sb.append("Error: Incorrect data.");
                        }
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

    public String exportRacersWithCars() {
        try {
            return this.jsonParser.write(this.racerService.getAllRacersWithCars());
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
