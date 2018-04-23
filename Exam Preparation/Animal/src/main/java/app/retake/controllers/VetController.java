package app.retake.controllers;

import app.retake.domain.dto.VetWrapperXMLImportDTO;
import app.retake.domain.dto.VetXMLImportDTO;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class VetController {

    private VetService vetService;
    private Parser parser;

    public VetController(VetService vetService, @Qualifier("XMLParser") Parser parser) {
        this.vetService = vetService;
        this.parser = parser;
    }

    public String importDataFromXML(String xmlContent){
        StringBuilder sb = new StringBuilder();
        try {
            VetWrapperXMLImportDTO vets = this.parser.read(VetWrapperXMLImportDTO.class, xmlContent);
            for (VetXMLImportDTO vet : vets.getVets()) {
                if (ValidationUtil.isValid(vet)) {
                    this.vetService.create(vet);
                    sb.append(String.format("Record %s successfully imported.", vet.getName()));
                } else {
                    sb.append("Error: Invalid data.");
                }
                sb.append(System.lineSeparator());
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            sb.append("Error: Invalid data.").append(System.lineSeparator());
        }
        return sb.toString();
    }
}
