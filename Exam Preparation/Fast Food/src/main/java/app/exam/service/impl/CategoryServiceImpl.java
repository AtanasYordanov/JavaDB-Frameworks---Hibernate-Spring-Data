package app.exam.service.impl;

import app.exam.domain.dto.xml.CategoriesFrequentItemsXMLExportDTO;
import app.exam.domain.dto.xml.CategoryExportDTO;
import app.exam.domain.dto.xml.MostPopularItemDTO;
import app.exam.domain.entities.Category;
import app.exam.domain.entities.Item;
import app.exam.domain.entities.OrderItem;
import app.exam.parser.interfaces.ModelParser;
import app.exam.repository.CategoryRepository;
import app.exam.repository.ItemsRepository;
import app.exam.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;
    private ItemsRepository itemsRepository;
    private ModelParser modelParser;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository
            , ItemsRepository itemsRepository, ModelParser modelParser) {
        this.categoryRepository = categoryRepository;
        this.itemsRepository = itemsRepository;
        this.modelParser = modelParser;
    }

    @Override
    public CategoriesFrequentItemsXMLExportDTO getCategoriesWithMostPopularItems(List<String> categoryNames) {
        CategoriesFrequentItemsXMLExportDTO categoriesWrapper = new CategoriesFrequentItemsXMLExportDTO();
        for (String categoryName : categoryNames) {
            Category category = this.categoryRepository.findByName(categoryName);
            CategoryExportDTO categoryDto = new CategoryExportDTO();
            categoryDto.setName(categoryName);
            Item item = category.getItems().stream()
                    .sorted((a, b) -> Integer.compare(b.getOrderItems().size(), a.getOrderItems().size()))
                    .findFirst().orElse(null);
            if (item != null){
                MostPopularItemDTO mostPopularItemDTO = new MostPopularItemDTO();
                Integer timesSold = item.getOrderItems().stream()
                        .map(OrderItem::getQuantity)
                        .mapToInt(Integer::valueOf)
                        .sum();
                mostPopularItemDTO.setTimesSold(timesSold);
                mostPopularItemDTO.setTotalMade(item.getPrice().multiply(BigDecimal.valueOf(timesSold)));
                categoryDto.setMostPopularItem(mostPopularItemDTO);
            }
            categoriesWrapper.getCategoriesWithMostFrequentItem().add(categoryDto);
        }
        return categoriesWrapper;
    }

    @Override
    public Category findByName(String name) {
        return this.categoryRepository.findByName(name);
    }
}
