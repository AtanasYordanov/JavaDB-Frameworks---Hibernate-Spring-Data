package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.model.dto.json.CarImportDtoJSON;
import org.softuni.mostwanted.model.entities.Car;
import org.softuni.mostwanted.model.entities.Racer;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.services.api.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private RacerRepository racerRepository;
    private ModelParser modelParser;

    public CarServiceImpl(CarRepository carRepository, RacerRepository racerRepository
            , ModelParser modelParser) {
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(CarImportDtoJSON carDto) {
        Car car = this.modelParser.convert(carDto, Car.class);
        Racer racer = this.racerRepository.findOneByName(carDto.getRacerName());
        if (racer == null) {
            throw new IllegalArgumentException();
        }
        car.setRacer(racer);
        racer.getCars().add(car);
        this.carRepository.saveAndFlush(car);
    }
}
