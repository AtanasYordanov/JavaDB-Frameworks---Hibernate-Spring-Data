package org.softuni.mostwanted.services.impl;

import org.softuni.mostwanted.model.dto.xml.EntryImportDtoXML;
import org.softuni.mostwanted.model.dto.xml.RaceImportDtoXML;
import org.softuni.mostwanted.model.entities.District;
import org.softuni.mostwanted.model.entities.Race;
import org.softuni.mostwanted.model.entities.RaceEntry;
import org.softuni.mostwanted.parser.interfaces.ModelParser;
import org.softuni.mostwanted.repositories.DistrictRepository;
import org.softuni.mostwanted.repositories.RaceEntryRepository;
import org.softuni.mostwanted.repositories.RaceRepository;
import org.softuni.mostwanted.services.api.RaceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class RaceServiceImpl implements RaceService{

    private RaceRepository raceRepository;
    private DistrictRepository districtRepository;
    private RaceEntryRepository raceEntryRepository;
    private ModelParser modelParser;

    public RaceServiceImpl(RaceRepository raceRepository, DistrictRepository districtRepository
            , RaceEntryRepository raceEntryRepository, ModelParser modelParser) {
        this.raceRepository = raceRepository;
        this.districtRepository = districtRepository;
        this.raceEntryRepository = raceEntryRepository;
        this.modelParser = modelParser;
    }

    @Override
    public long getLastId() {
        Race race = this.raceRepository.getLastRaceEntryId();
        return race == null ? 0 : race.getId();
    }

    @Override
    public void create(RaceImportDtoXML raceDto) {
        Race race = this.modelParser.convert(raceDto, Race.class);
        District district = this.districtRepository.findByName(raceDto.getDistrictName());
        if (district == null) {
            throw new IllegalArgumentException();
        }
        race.setDistrict(district);
        Set<RaceEntry> raceEntries = new HashSet<>();
        for (EntryImportDtoXML entryDto : raceDto.getEntries()) {
            RaceEntry raceEntry = this.raceEntryRepository.findOne(entryDto.getId());
            if (raceEntry != null) {
                raceEntries.add(raceEntry);
                raceEntry.setRace(race);
            }
        }
        race.setEntries(raceEntries);
        this.raceRepository.saveAndFlush(race);
    }
}
