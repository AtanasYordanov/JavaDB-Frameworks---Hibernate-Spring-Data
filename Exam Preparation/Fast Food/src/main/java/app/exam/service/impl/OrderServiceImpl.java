package app.exam.service.impl;

import app.exam.domain.dto.json.EmployeeOrdersJSONExportDTO;
import app.exam.domain.dto.json.ItemJSONExportDTO;
import app.exam.domain.dto.json.OrderJSONExportDTO;
import app.exam.domain.dto.xml.OrderItemXMLImportDTO;
import app.exam.domain.dto.xml.OrderXMLImportDTO;
import app.exam.domain.entities.*;
import app.exam.parser.interfaces.ModelParser;
import app.exam.repository.ItemsRepository;
import app.exam.repository.OrderRepository;
import app.exam.service.api.EmployeeService;
import app.exam.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private EmployeeService employeeService;
    private ItemsRepository itemsRepository;
    private ModelParser modelParser;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, EmployeeService employeeService, ItemsRepository itemsRepository, ModelParser modelParser) {
        this.orderRepository = orderRepository;
        this.employeeService = employeeService;
        this.itemsRepository = itemsRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(OrderXMLImportDTO dto) throws ParseException {
        Order order = this.modelParser.convert(dto, Order.class);
        Employee employee = this.employeeService.findByName(dto.getEmployee());
        OrderType orderType = OrderType.valueOf(dto.getType().toUpperCase());
        order.setOrderType(orderType);
        if (employee == null){
            throw new IllegalArgumentException();
        }
        order.setEmployee(employee);
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemXMLImportDTO itemDto : dto.getItems()) {
            Item item = this.itemsRepository.findByName(itemDto.getName());
            if (item == null){
                throw new IllegalArgumentException();
            }
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setOrder(order);
            orderItem.setQuantity(itemDto.getQuantity());
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        this.orderRepository.saveAndFlush(order);
    }

    @Override
    public EmployeeOrdersJSONExportDTO exportOrdersByEmployeeAndOrderType(String employeeName, String orderType) {
        EmployeeOrdersJSONExportDTO employeeOrders = new EmployeeOrdersJSONExportDTO();
        employeeOrders.setEmployeeName(employeeName);
        List<Order> orders = this.orderRepository
                .findByEmployeeAndType(employeeName, OrderType.valueOf(orderType.toUpperCase()));
        orders.sort((Comparator.comparing(Order::getTotalPrice, Comparator.reverseOrder())
                .thenComparing(Order::getItemsCount)));
        List<OrderJSONExportDTO> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            OrderJSONExportDTO orderDto = this.modelParser.convert(order, OrderJSONExportDTO.class);
            orderDto.setItems(new ArrayList<>());
            List<OrderItem> tempList = order.getOrderItems().stream()
                    .sorted(Comparator.comparing(a -> a.getItem().getId()))
                    .collect(Collectors.toList());
            for (OrderItem orderItem : tempList) {
                Item item = orderItem.getItem();
                ItemJSONExportDTO itemDto = new ItemJSONExportDTO();
                itemDto.setName(item.getName());
                itemDto.setPrice(item.getPrice());
                itemDto.setQuantity(orderItem.getQuantity());
                orderDto.getItems().add(itemDto);
            }
            orderDtos.add(orderDto);
        }
        employeeOrders.setOrders(orderDtos);
        return employeeOrders;
    }
}
