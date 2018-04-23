package app.retake.controllers;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class AnimalController {

    private AnimalService animalService;
    private Parser parser;

    @Autowired
    public AnimalController(AnimalService animalService, @Qualifier("JSONParser") Parser parser) {
        this.animalService = animalService;
        this.parser = parser;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            AnimalJSONImportDTO[] animals = this.parser.read(AnimalJSONImportDTO[].class, jsonContent);
            for (AnimalJSONImportDTO animal : animals) {
                if (ValidationUtil.isValid(animal)) {
                    try {
                        this.animalService.create(animal);
                        sb.append(String.format("Record %s Passport №: %s successfully imported."
                                , animal.getName(), animal.getPassport().getSerialNumber()));
                    }catch (IllegalArgumentException e){
                        sb.append(sb.append("Error: Invalid data."));
                    }
                } else {
                    sb.append("Error: Invalid data.");
                }
                sb.append(System.lineSeparator());
            }
        } catch (JAXBException | ParseException | IOException e) {
            e.printStackTrace();
            sb.append("Error: Invalid data.").append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String exportAnimalsByOwnerPhoneNumber(String phoneNumber) {
        try {
            return this.parser.write(this.animalService.findByOwnerPhoneNumber(phoneNumber));
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }
}
