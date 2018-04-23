package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.model.dto.json.DistrictImportDtoJSON;
import org.softuni.mostwanted.model.entities.District;
import org.softuni.mostwanted.model.entities.Town;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.repositories.TownRepository;
import org.softuni.mostwanted.services.api.DistrictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DistrictServiceImpl implements DistrictService {

    private DistrictRepository districtRepository;
    private TownRepository townRepository;
    private ModelParser modelParser;

    public DistrictServiceImpl(DistrictRepository districtRepository, TownRepository townRepository
            , ModelParser modelParser) {
        this.districtRepository = districtRepository;
        this.townRepository = townRepository;
        this.modelParser = modelParser;
    }

    @Override
    public boolean contains(String name) {
        return this.districtRepository.findByName(name) != null;
    }

    @Override
    public void create(DistrictImportDtoJSON districtDto) {
        District district = this.modelParser.convert(districtDto, District.class);
        Town town = this.townRepository.findByName(districtDto.getTownName());
        if (town == null){
            throw new IllegalArgumentException();
        }
        district.setTown(town);
        this.districtRepository.saveAndFlush(district);
    }
}
