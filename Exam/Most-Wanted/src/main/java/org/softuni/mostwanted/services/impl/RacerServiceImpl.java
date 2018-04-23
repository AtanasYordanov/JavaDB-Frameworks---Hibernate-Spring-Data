package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.model.dto.json.RacerCarsExportDtoJSON;
import org.softuni.mostwanted.model.dto.json.RacerImportDtoJSON;
import org.softuni.mostwanted.model.entities.Car;
import org.softuni.mostwanted.model.entities.Racer;
import org.softuni.mostwanted.model.entities.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.api.RacerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RacerServiceImpl implements RacerService{

    private RacerRepository racerRepository;
    private TownRepository townRepository;
    private ModelParser modelParser;

    @Autowired
    public RacerServiceImpl(RacerRepository racerRepository, TownRepository townRepository
            , ModelParser modelParser) {
        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
        this.modelParser = modelParser;
    }

    @Override
    public boolean contains(String name) {
        return this.racerRepository.findOneByName(name) != null;
    }

    @Override
    public void create(RacerImportDtoJSON racerDto) {
        Racer racer = this.modelParser.convert(racerDto, Racer.class);
        Town town = this.townRepository.findByName(racerDto.getHomeTown());
        if (town == null){
            throw new IllegalArgumentException();
        }
        racer.setHomeTown(town);
        town.getRacers().add(racer);
        this.racerRepository.saveAndFlush(racer);
    }

    @Override
    public List<RacerCarsExportDtoJSON> getAllRacersWithCars() {
        List<Racer> racers = this.racerRepository.getAllRacersWithCars();
        List<RacerCarsExportDtoJSON> racerCarsDtos = new ArrayList<>();
        for (Racer racer : racers) {
            RacerCarsExportDtoJSON racerCarsDto = new RacerCarsExportDtoJSON();
            racerCarsDto.setName(racer.getName());
            racerCarsDto.setAge(racer.getAge());
            for (Car car : racer.getCars()) {
                String carAsString = String.format("%s %s %s"
                        , car.getBrand(), car.getModel(), car.getYearOfProduction());
                racerCarsDto.getCars().add(carAsString);
            }
            racerCarsDtos.add(racerCarsDto);
        }
        return racerCarsDtos;
    }
}
