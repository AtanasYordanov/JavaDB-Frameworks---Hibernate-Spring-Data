package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.model.dto.xml.RaceEntriesImportWrapperDtoXML;
import org.softuni.mostwanted.model.dto.xml.RaceEntryImportDtoXML;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.RaceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class RaceEntryController {

    private RaceEntryService raceEntryService;
    private Parser xmlParser;

    @Autowired
    public RaceEntryController(RaceEntryService raceEntryService, @Qualifier("XMLParser") Parser xmlParser) {
        this.raceEntryService = raceEntryService;
        this.xmlParser = xmlParser;
    }

    public String importRaceEntriesFromXML(String xmlContent) {
        StringBuilder sb = new StringBuilder();
        long lastAddedId = this.raceEntryService.getLastId();
        try {
            RaceEntriesImportWrapperDtoXML raceEntryDtos = this.xmlParser.read(RaceEntriesImportWrapperDtoXML.class, xmlContent);
            for (RaceEntryImportDtoXML raceEntryDto : raceEntryDtos.getRaceEntries()) {
                if (ValidationUtil.isValid(raceEntryDto)) {
                    try {
                        this.raceEntryService.create(raceEntryDto);
                        sb.append(String.format("Successfully created RaceEntry - %d.", ++lastAddedId));
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

    public String exportMostWantedRacer() {
        try {
            return this.xmlParser.write(this.raceEntryService.getMostWantedRacer());
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
