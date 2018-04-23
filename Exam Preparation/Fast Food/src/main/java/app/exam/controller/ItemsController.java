package app.exam.controller;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.parser.ValidationUtil;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class ItemsController {

    private ItemsService itemsService;
    private Parser jsonParser;

    @Autowired
    public ItemsController(ItemsService itemsService, @Qualifier("JSONParser") Parser jsonParser) {
        this.itemsService = itemsService;
        this.jsonParser = jsonParser;
    }

    public String importDataFromJSON(String jsonContent) {
        StringBuilder sb = new StringBuilder();
        try {
            ItemJSONImportDTO[] itemDtos = this.jsonParser.read(ItemJSONImportDTO[].class, jsonContent);
            for (ItemJSONImportDTO itemDto : itemDtos) {
                if (ValidationUtil.isValid(itemDto)) {
                    try {
                        this.itemsService.create(itemDto);
                        sb.append(String.format("Record %s successfully imported.", itemDto.getName()));
                    } catch (IllegalArgumentException e) {
                        sb.append("Error: Invalid data.");
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
