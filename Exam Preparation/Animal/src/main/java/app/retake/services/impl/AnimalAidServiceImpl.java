package app.retake.services.impl;

import app.retake.domain.dto.AnimalAidJSONImportDTO;
import app.retake.domain.models.AnimalAid;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalAidRepository;
import app.retake.services.api.AnimalAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnimalAidServiceImpl implements AnimalAidService {

    private AnimalAidRepository animalAidRepository;
    private ModelParser mapper;

    @Autowired
    public AnimalAidServiceImpl(AnimalAidRepository animalAidRepository, ModelParser mapper) {
        this.animalAidRepository = animalAidRepository;
        this.mapper = mapper;
    }

    @Override
    public void create(AnimalAidJSONImportDTO dto) {
        AnimalAid animalAid = this.animalAidRepository.findByName(dto.getName());
        if (animalAid != null) {
            animalAid.setPrice(dto.getPrice());
        } else {
            animalAid = this.mapper.convert(dto, AnimalAid.class);
        }
        this.animalAidRepository.saveAndFlush(animalAid);
    }

    @Override
    public AnimalAid getOneByName(String name) {
        return this.animalAidRepository.findByName(name);
    }
}
