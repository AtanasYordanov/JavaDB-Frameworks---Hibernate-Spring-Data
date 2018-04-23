package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.model.dto.json.TownExportDtoJSON;
import org.softuni.mostwanted.model.dto.json.TownImportDtoJSON;
import org.softuni.mostwanted.model.entities.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.api.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class TownServiceImpl implements TownService {

    private TownRepository townRepository;
    private ModelParser modelParser;


    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelParser modelParser) {
        this.townRepository = townRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(TownImportDtoJSON townDto) {
        Town town = this.modelParser.convert(townDto, Town.class);
        this.townRepository.saveAndFlush(town);
    }

    @Override
    public boolean contains(String name) {
        return this.townRepository.findByName(name) != null;
    }

    @Override
    public List<TownExportDtoJSON> getAllRacingTowns() {
        List<Town> towns = this.townRepository.findAll();
        List<TownExportDtoJSON> townDtos = new ArrayList<>();
        for (Town town : towns) {
            if (town.getRacers().size() > 0){
                TownExportDtoJSON townDto = new TownExportDtoJSON();
                townDto.setName(town.getName());
                townDto.setRacers(town.getRacers().size());
                townDtos.add(townDto);
            }
        }
        townDtos.sort(Comparator.comparing(TownExportDtoJSON::getRacers, Comparator.reverseOrder()));
        return townDtos;
    }
}
