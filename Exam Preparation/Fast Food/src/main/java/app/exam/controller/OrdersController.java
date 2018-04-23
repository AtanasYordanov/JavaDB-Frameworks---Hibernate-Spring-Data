package app.exam.controller;

import app.exam.domain.dto.xml.OrderWrapperXMLImportDTO;
import app.exam.domain.dto.xml.OrderXMLImportDTO;
import app.exam.parser.ValidationUtil;
import app.exam.parser.interfaces.Parser;
import app.exam.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.text.ParseException;

@Controller
public class OrdersController {

    private OrderService orderService;
    private Parser xmlParser;
    private Parser jsonParser;

    @Autowired
    public OrdersController(OrderService orderService, @Qualifier("XMLParser") Parser jsonParser
            , @Qualifier("JSONParser") Parser jsonParser1) {
        this.orderService = orderService;
        this.xmlParser = jsonParser;
        this.jsonParser = jsonParser1;
    }

    public String importDataFromXML(String xmlContent) {
        StringBuilder sb = new StringBuilder();
        try {
            OrderWrapperXMLImportDTO orderDtos = this.xmlParser.read(OrderWrapperXMLImportDTO.class, xmlContent);
            for (OrderXMLImportDTO orderDto : orderDtos.getOrders()) {
                if (ValidationUtil.isValid(orderDto)) {
                    try {
                        this.orderService.create(orderDto);
                        sb.append(String.format("Order for %s on %s added.", orderDto.getCustomer(), orderDto.getDate()));
                    } catch (IllegalArgumentException e) {
                        sb.append("Error: Invalid data.");
                    }
                } else {
                    sb.append("Error: Invalid data.");
                }
                sb.append(System.lineSeparator());
            }
        } catch (IOException | JAXBException | ParseException e) {
            e.printStackTrace();
            sb.append("Error: Invalid data.").append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) {
        try {
            return this.jsonParser.write(this.orderService.exportOrdersByEmployeeAndOrderType(employeeName, orderType));
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
