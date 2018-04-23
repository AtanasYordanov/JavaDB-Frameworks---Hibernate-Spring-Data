package org.softuni.mostwanted.terminal;

import org.softuni.mostwanted.constants.Config;
import org.softuni.mostwanted.controllers.*;
import org.softuni.mostwanted.io.interfaces.ConsoleIO;
import org.softuni.mostwanted.io.interfaces.FileIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class Terminal implements CommandLineRunner {

    private CarController carController;
    private DistrictController districtController;
    private TownController townController;
    private RaceController raceController;
    private RacerController racerController;
    private RaceEntryController raceEntryController;
    private ConsoleIO writer;
    private FileIO fileIO;


    @Autowired
    public Terminal(CarController carController, DistrictController districtController, TownController townController
            , RaceController raceController, RacerController racerController, RaceEntryController raceEntryController, ConsoleIO writer, FileIO fileIO) {
        this.carController = carController;
        this.districtController = districtController;
        this.townController = townController;
        this.raceController = raceController;
        this.racerController = racerController;
        this.raceEntryController = raceEntryController;
        this.writer = writer;
        this.fileIO = fileIO;
    }

    @Override
    public void run(String... args) throws Exception {
        // Import Data
        // JSON
        this.writer.write(this.townController.importTownsFromJSON(
                this.fileIO.read(Config.TOWNS_IMPORT_JSON_PATH)));
        this.writer.write(this.districtController.importDistrictsFromJSON(
                this.fileIO.read(Config.DISTRICTS_IMPORT_JSON_PATH)));
        this.writer.write(this.racerController.importRacersFromJSON(
                this.fileIO.read(Config.RACERS_IMPORT_JSON_PATH)));
        this.writer.write(this.carController.importCarsFromJSON(
                this.fileIO.read(Config.CARS_IMPORT_JSON_PATH)));
        // XML
        this.writer.write(this.raceEntryController.importRaceEntriesFromXML(
                this.fileIO.read(Config.RACE_ENTRIES_IMPORT_XML_PATH)));
        this.writer.write(this.raceController.importRacesFromXML(
                this.fileIO.read(Config.RACES_IMPORT_XML_PATH)));

        // Export Data
        // JSON
        this.fileIO.write(this.townController.exportRacingTowns()
                , Config.RACING_TOWNS_EXPORT_PATH_JSON);
        this.fileIO.write(this.racerController.exportRacersWithCars()
                , Config.RACERS_CARS_EXPORT_PATH_JSON);
        // XML
        this.fileIO.write(this.raceEntryController.exportMostWantedRacer()
                , Config.MOST_WANTED_EXPORT_PATH_XML);
    }
}
