package app.exam.terminal;

import app.exam.config.Config;
import app.exam.controller.CategoryController;
import app.exam.controller.EmployeesController;
import app.exam.controller.ItemsController;
import app.exam.controller.OrdersController;
import app.exam.io.interfaces.ConsoleIO;
import app.exam.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Terminal implements CommandLineRunner {

    private CategoryController categoryController;
    private EmployeesController employeesController;
    private ItemsController itemsController;
    private OrdersController ordersController;
    private ConsoleIO writer;
    private FileIO fileIO;

    @Autowired
    public Terminal(CategoryController categoryController, EmployeesController employeesController
            , ItemsController itemsController, OrdersController ordersController, ConsoleIO writer, FileIO fileIO) {
        this.categoryController = categoryController;
        this.employeesController = employeesController;
        this.itemsController = itemsController;
        this.ordersController = ordersController;
        this.writer = writer;
        this.fileIO = fileIO;
    }

    @Override
    public void run(String... args) throws Exception {
//        this.writer.write(this.employeesController.importDataFromJSON(
//                this.fileIO.read(Config.EMPLOYEES_IMPORT_JSON)));
//
//        this.writer.write(this.itemsController.importDataFromJSON(
//                this.fileIO.read(Config.ITEMS_IMPORT_JSON)));
//
//        this.writer.write(this.ordersController.importDataFromXML(
//                this.fileIO.read(Config.ORDERS_IMPORT_XML)));

//        this.fileIO.write(this.ordersController.exportOrdersByEmployeeAndOrderType(
//                "Avery Rush", "ToGo")
//                , "./src/main/resources/files/output/orders-by-employee-and-type.json");

        List<String> names = new ArrayList<>();
        names.add("Chicken");
        names.add("Toys");
        names.add("Drinks");
        this.fileIO.write(this.categoryController.getCategoriesWithMostPopularItemsSorted(names)
                , "./src/main/resources/files/output/categories-most-popular-items.xml");
    }
}
