package app.retake.controllers;

import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.AnimalAidService;
import com.google.gson.stream.JsonReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class AnimalAidController {

    private AnimalAidService animalAidService;
    private Parser parser;

    @Autowired
    public AnimalAidController(AnimalAidService animalAidService, @Qualifier("JSONParser") Parser parser) {
        this.animalAidService = animalAidService;
        this.parser = parser;
    }

    public String importDataFromJSON(String jsonContent) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            AnimalAidJSONImportDTO[] animalAids = this.parser.read(AnimalAidJSONImportDTO[].class, jsonContent);
            for (AnimalAidJSONImportDTO animalAid : animalAids) {
                if (ValidationUtil.isValid(animalAid)) {
                    this.animalAidService.create(animalAid);
                    sb.append(String.format("Record %s successfully imported.", animalAid.getName()));
                } else {
                    sb.append("Error: Invalid data.");
                }
                sb.append(System.lineSeparator());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
            sb.append("Error: Invalid data.").append(System.lineSeparator());
        }
        return sb.toString();
    }
}
