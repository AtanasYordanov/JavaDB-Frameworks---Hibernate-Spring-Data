package app.retake.services.impl;

import app.retake.domain.dto.AnimalJSONImportDTO;
import app.retake.domain.dto.AnimalsJSONExportDTO;
import app.retake.domain.models.Animal;
import app.retake.domain.models.Passport;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.AnimalRepository;
import app.retake.repositories.PassportRepository;
import app.retake.services.api.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {

    private PassportRepository passportRepository;
    private AnimalRepository animalRepository;
    private ModelParser mapper;

    @Autowired
    public AnimalServiceImpl(PassportRepository passportRepository, AnimalRepository animalRepository, ModelParser mapper) {
        this.passportRepository = passportRepository;
        this.animalRepository = animalRepository;
        this.mapper = mapper;
    }

    @Override
    public void create(AnimalJSONImportDTO dto) throws ParseException {
        Animal animal = this.mapper.convert(dto, Animal.class);
        if (this.passportRepository.findOne(animal.getPassport().getSerialNumber()) != null)
            throw new IllegalArgumentException();
        Passport passport = this.mapper.convert(dto.getPassport(), Passport.class);
        animal.setPassport(passport);
        this.animalRepository.saveAndFlush(animal);
    }

    @Override
    public List<AnimalsJSONExportDTO> findByOwnerPhoneNumber(String phoneNumber) {
        List<Animal> animals = this.animalRepository.findByOwnerPhoneNumber(phoneNumber);
        List<AnimalsJSONExportDTO> animalDtos = new ArrayList<>();
        for (Animal animal : animals) {
            AnimalsJSONExportDTO animalDto = this.mapper.convert(animal, AnimalsJSONExportDTO.class);
            animalDto.setOwnerName(animal.getPassport().getOwnerName());
            animalDto.setRegistrationDate(animal.getPassport().getRegistrationDate());
            animalDto.setSerialNumber(animal.getPassport().getSerialNumber());
            animalDtos.add(animalDto);
        }
        return animalDtos;
    }

    @Override
    public Animal getByPassportSerialNumber(String serialNumber) {
        return this.animalRepository.findOneByPassportSerialNumber(serialNumber);
    }
}
