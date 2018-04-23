package app.exam.controller;

import app.exam.domain.dto.json.EmployeeJSONImportDTO;
import app.exam.parser.ValidationUtil;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class EmployeesController {

    private EmployeeService employeeService;
    private Parser jsonParser;

    @Autowired
    public EmployeesController(EmployeeService employeeService, @Qualifier("JSONParser") Parser jsonParser) {
        this.employeeService = employeeService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent){
        StringBuilder sb = new StringBuilder();
        try {
            EmployeeJSONImportDTO[] employeeDtos = this.jsonParser.read(EmployeeJSONImportDTO[].class, jsonContent);
            for (EmployeeJSONImportDTO employeeDto : employeeDtos) {
                if (ValidationUtil.isValid(employeeDto)){
                    this.employeeService.create(employeeDto);
                    sb.append(String.format("Record %s successfully imported.", employeeDto.getName()));
                }else{
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
