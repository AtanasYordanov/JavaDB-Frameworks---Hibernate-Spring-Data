package app.exam.controller;

import app.exam.parser.interfaces.Parser;
import app.exam.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

@Controller
public class CategoryController {

    private CategoryService categoryService;
    private Parser xmlParser;

    @Autowired
    public CategoryController(CategoryService categoryService, @Qualifier("XMLParser") Parser xmlParser) {
        this.categoryService = categoryService;
        this.xmlParser = xmlParser;
    }

    public String getCategoriesWithMostPopularItemsSorted(List<String> categoryNames){
        try {
            return this.xmlParser.write(this.categoryService.getCategoriesWithMostPopularItems(categoryNames));
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
