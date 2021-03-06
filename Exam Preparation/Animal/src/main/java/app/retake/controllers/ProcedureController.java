package app.retake.controllers;

import app.retake.domain.dto.ProcedureWrapperXMLImportDTO;
import app.retake.domain.dto.ProcedureXMLImportDTO;
import app.retake.parser.ValidationUtil;
import app.retake.parser.interfaces.Parser;
import app.retake.services.api.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class ProcedureController {

    private ProcedureService procedureService;
    private Parser parser;

    @Autowired
    public ProcedureController(ProcedureService procedureService, @Qualifier("XMLParser") Parser parser) {
        this.procedureService = procedureService;
        this.parser = parser;
    }

    public String importDataFromXML(String xmlContent){
        StringBuilder sb = new StringBuilder();
        try {
            ProcedureWrapperXMLImportDTO procedures = this.parser.read(ProcedureWrapperXMLImportDTO.class, xmlContent);
            for (ProcedureXMLImportDTO procedure : procedures.getProcedures()) {
                if (ValidationUtil.isValid(procedure)) {
                    try {
                        this.procedureService.create(procedure);
                        sb.append("Record successfully imported.");
                    }catch (IllegalArgumentException e){
                        sb.append("Error: Invalid data.");
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

    public String exportProcedures() throws IOException, JAXBException {
        return this.parser.write(this.procedureService.exportProcedures());
    }
}
