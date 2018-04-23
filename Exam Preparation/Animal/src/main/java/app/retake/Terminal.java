package app.retake;

import app.retake.controllers.AnimalAidController;
import app.retake.controllers.AnimalController;
import app.retake.controllers.ProcedureController;
import app.retake.controllers.VetController;
import app.retake.io.FIleIOImpl;
import app.retake.io.api.ConsoleIO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {

    private AnimalAidController animalAidController;
    private AnimalController animalController;
    private ProcedureController procedureController;
    private VetController vetController;
    private FIleIOImpl fileIo;
    private ConsoleIO writer;

    @Autowired
    public Terminal(AnimalAidController animalAidController, AnimalController animalController,
                    ProcedureController procedureController, VetController vetController, ConsoleIO writer) {
        this.animalAidController = animalAidController;
        this.animalController = animalController;
        this.procedureController = procedureController;
        this.vetController = vetController;
        this.writer = writer;
        this.fileIo = new FIleIOImpl();
    }


    @Override
    public void run(String... strings) throws Exception {
        this.writer.write(this.animalAidController.importDataFromJSON(
                this.fileIo.read(Config.ANIMAL_AIDS_IMPORT_JSON)));

        this.writer.write(this.animalController.importDataFromJSON(
                this.fileIo.read(Config.ANIMALS_IMPORT_JSON)));

        this.writer.write(this.vetController.importDataFromXML(
                this.fileIo.read(Config.VETS_IMPORT_XML)));

        this.writer.write(this.procedureController.importDataFromXML(
                this.fileIo.read(Config.PROCEDURES_IMPORT_XML)));

        this.fileIo.write(this.animalController
                .exportAnimalsByOwnerPhoneNumber("0887446123"),
                "./src/main/resources/files/animals-by-phone-number.json");

        this.fileIo.write(this.procedureController
                        .exportProcedures(),
                "./src/main/resources/files/procedures.json");

    }
}
