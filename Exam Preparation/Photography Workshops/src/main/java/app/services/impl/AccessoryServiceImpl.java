package app.services.impl;

import app.constants.Messages;
import app.io.Writer;
import app.model.dtos.binding.AccessoryImportDto;
import app.model.entities.Accessory;
import app.model.entities.Photographer;
import app.repositories.AccessoryRepository;
import app.repositories.PhotographerRepository;
import app.services.api.AccessoryService;
import app.utils.mapper.MapperConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class AccessoryServiceImpl implements AccessoryService {

    private AccessoryRepository accessoryRepository;
    private PhotographerRepository photographerRepository;
    private MapperConverter mapper;
    private Writer writer;

    @Autowired
    public AccessoryServiceImpl(AccessoryRepository accessoryRepository
            , PhotographerRepository photographerRepository, MapperConverter mapper, Writer writer) {
        this.accessoryRepository = accessoryRepository;
        this.photographerRepository = photographerRepository;
        this.mapper = mapper;
        this.writer = writer;
    }

    @Override
    public void save(Accessory accessory) {
        this.accessoryRepository.save(accessory);
    }

    @Override
    public void saveAll(List<AccessoryImportDto> accessories) {
        Random random = new Random();
        List<Photographer> photographers = this.photographerRepository.findAll();
        for (AccessoryImportDto accessoryDto : accessories) {
            Accessory accessory = this.mapper.convert(accessoryDto, Accessory.class);
            int randomPhotographerIndex = random.nextInt(photographers.size());
            Photographer owner = photographers.get(randomPhotographerIndex);
            accessory.setOwner(owner);
            this.save(accessory);
            this.writer.println(Messages.SUCCESSFULLY_IMPORTED_ACCESSORY_MESSAGE, accessory.getName());
        }
    }
}
