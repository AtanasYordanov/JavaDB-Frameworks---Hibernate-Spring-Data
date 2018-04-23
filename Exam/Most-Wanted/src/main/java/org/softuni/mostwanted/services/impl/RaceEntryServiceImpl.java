package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.model.dto.xml.MostWantedEntryExportDtoXML;
import org.softuni.mostwanted.model.dto.xml.MostWantedExportDtoXML;
import org.softuni.mostwanted.model.dto.xml.MostWantedExportWrapperDtoXML;
import org.softuni.mostwanted.model.dto.xml.RaceEntryImportDtoXML;
import org.softuni.mostwanted.model.entities.Car;
import org.softuni.mostwanted.model.entities.RaceEntry;
import org.softuni.mostwanted.model.entities.Racer;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.CarRepository;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.repositories.RacerRepository;
import org.softuni.mostwanted.services.api.RaceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class RaceEntryServiceImpl implements RaceEntryService{

    private RaceEntryRepository raceEntryRepository;
    private RacerRepository racerRepository;
    private CarRepository carRepository;
    private ModelParser modelParser;

    @Autowired
    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository, RacerRepository racerRepository
            , CarRepository carRepository, ModelParser modelParser) {
        this.raceEntryRepository = raceEntryRepository;
        this.racerRepository = racerRepository;
        this.carRepository = carRepository;
        this.modelParser = modelParser;
    }

    @Override
    public Long getLastId() {
        RaceEntry raceEntry = this.raceEntryRepository.getLastRaceEntryId();
        return raceEntry == null ? 0 : raceEntry.getId();
    }

    @Override
    public void create(RaceEntryImportDtoXML raceEntryDto) {
        RaceEntry raceEntry = this.modelParser.convert(raceEntryDto, RaceEntry.class);
        Racer racer = this.racerRepository.findOneByName(raceEntryDto.getRacerName());
        Car car = this.carRepository.findOne(raceEntryDto.getCarId());
        if (racer == null || car == null) {
            throw new IllegalArgumentException();
        }
        raceEntry.setRacer(racer);
        raceEntry.setCar(car);
        raceEntry.setRace(null);
        this.raceEntryRepository.saveAndFlush(raceEntry);
    }

    @Override
    public MostWantedExportWrapperDtoXML getMostWantedRacer() {
        MostWantedExportWrapperDtoXML mostWantedWrapperDto = new MostWantedExportWrapperDtoXML();
        List<RaceEntry> raceEntries = this.raceEntryRepository.getMostWantedRacerEntries();
        MostWantedExportDtoXML mostWantedRacerDto = new MostWantedExportDtoXML();
        mostWantedRacerDto.setName(raceEntries.get(0).getRacer().getName());
        for (RaceEntry raceEntry : raceEntries) {
            MostWantedEntryExportDtoXML raceEntryDto = new MostWantedEntryExportDtoXML();
            Car car = raceEntry.getCar();
            String carAsString = String.format("%s %s @ %d",
                    car.getBrand(), car.getModel(), car.getYearOfProduction());
            raceEntryDto.setFinishTime(raceEntry.getFinishTime());
            raceEntryDto.setCar(carAsString);
            mostWantedRacerDto.getEntries().add(raceEntryDto);
        }
        mostWantedRacerDto.getEntries().sort(Comparator.comparing(MostWantedEntryExportDtoXML::getFinishTime));
        mostWantedWrapperDto.setRacer(mostWantedRacerDto);
        return mostWantedWrapperDto;
    }
}
