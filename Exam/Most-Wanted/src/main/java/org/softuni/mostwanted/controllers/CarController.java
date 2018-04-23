package org.softuni.mostwanted.controllers;

import org.softuni.mostwanted.model.dto.json.CarImportDtoJSON;
import org.softuni.mostwanted.parser.ValidationUtil;
import org.softuni.mostwanted.parser.interfaces.Parser;
import org.softuni.mostwanted.services.api.CarService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class CarController {

    private CarService carService;
    private Parser jsonParser;

    public CarController(CarService carService, @Qualifier("JSONParser") Parser jsonParser) {
        this.carService = carService;
        this.jsonParser = jsonParser;
    }

    public String importCarsFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            CarImportDtoJSON[] carDtos = this.jsonParser.read(CarImportDtoJSON[].class, jsonContent);
            for (CarImportDtoJSON carDto : carDtos) {
                if (ValidationUtil.isValid(carDto)) {
                    try {
                        this.carService.create(carDto);
                        sb.append(String.format("Successfully created Car - %s %s @ %d.",
                                carDto.getBrand(), carDto.getModel(), carDto.getYearOfProduction()));
                    } catch (IllegalArgumentException e) {
                        sb.append("Error: Incorrect data.");
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
