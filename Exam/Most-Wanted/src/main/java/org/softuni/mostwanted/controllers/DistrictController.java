package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.model.dto.json.DistrictImportDtoJSON;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.DistrictService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class DistrictController {

    private DistrictService districtService;
    private Parser jsonParser;

    public DistrictController(DistrictService districtService, @Qualifier("JSONParser") Parser jsonParser) {
        this.districtService = districtService;
        this.jsonParser = jsonParser;
    }

    public String importDistrictsFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            DistrictImportDtoJSON[] districtDtos = this.jsonParser.read(DistrictImportDtoJSON[].class, jsonContent);
            for (DistrictImportDtoJSON districtDto : districtDtos) {
                if (ValidationUtil.isValid(districtDto)) {
                    if (this.districtService.contains(districtDto.getName())) {
                        sb.append("Error: Duplicate data.");
                    } else {
                        try {
                            this.districtService.create(districtDto);
                            sb.append(String.format("Successfully created District - %s.", districtDto.getName()));
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
}
