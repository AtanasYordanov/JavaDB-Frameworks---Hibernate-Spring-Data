package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.model.dto.xml.RaceImportDtoXML;
import org.softuni.mostwanted.model.dto.xml.RaceImportWrapperDtoXML;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RaceController {

    private RaceService raceService;
    private Parser xmlParser;

    @Autowired
    public RaceController(RaceService raceService, @Qualifier("XMLParser") Parser xmlParser) {
        this.raceService = raceService;
        this.xmlParser = xmlParser;
    }

    public String importRacesFromXML(String xmlContent) {
        StringBuilder sb = new StringBuilder();
        long lastAddedId = this.raceService.getLastId();
        try {
            RaceImportWrapperDtoXML raceDtos = this.xmlParser.read(RaceImportWrapperDtoXML.class, xmlContent);
            for (RaceImportDtoXML raceDto : raceDtos.getRaces()) {
                if (ValidationUtil.isValid(raceDto)) {
                    try {
                        this.raceService.create(raceDto);
                        sb.append(String.format("Successfully created Race - %d.", ++lastAddedId));
                    } catch (IllegalArgumentException e) {
                        sb.append("Error: Incorrect data.");
                    }
                } else {
                    sb.append("Error: Invalid data.");
                }
                sb.append(System.lineSeparator());
            }
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            sb.append("Error: Invalid data.").append(System.lineSeparator());
        }
        return sb.toString();
    }
}
