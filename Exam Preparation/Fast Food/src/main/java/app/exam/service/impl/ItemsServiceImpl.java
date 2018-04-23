package app.exam.service.impl;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.domain.entities.Category;
import app.exam.domain.entities.Item;
import app.exam.parser.interfaces.ModelParser;
import app.exam.repository.CategoryRepository;
import app.exam.repository.ItemsRepository;
import app.exam.service.api.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ItemsServiceImpl implements ItemsService {

    private ItemsRepository itemsRepository;
    private CategoryRepository categoryRepository;
    private ModelParser modelParser;

    @Autowired
    public ItemsServiceImpl(ItemsRepository itemsRepository
            , CategoryRepository categoryRepository, ModelParser modelParser) {
        this.itemsRepository = itemsRepository;
        this.categoryRepository = categoryRepository;

        this.modelParser = modelParser;
    }

    @Override
    public void create(ItemJSONImportDTO itemJSONImportDTO) {
        if (this.findByName(itemJSONImportDTO.getName()) != null){
            throw new IllegalArgumentException();
        }
        Item item = this.modelParser.convert(itemJSONImportDTO, Item.class);
        Category category = this.categoryRepository.findByName(itemJSONImportDTO.getCategory());
        category = category == null ? new Category(itemJSONImportDTO.getCategory()) : category;
        item.setCategory(category);
        category.getItems().add(item);
        this.categoryRepository.saveAndFlush(category);
        this.itemsRepository.saveAndFlush(item);
    }

    @Override
    public Item findByName(String name) {
        return this.itemsRepository.findByName(name);
    }
}
