package app.terminal;

import app.controllers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {

    private AccessoryController accessoryController;
    private CameraController cameraController;
    private LensController lensController;
    private PhotographerController photographerController;
    private WorkshopController workshopController;

    @Autowired
    public Terminal(AccessoryController accessoryController, CameraController cameraController,
                    LensController lensController, PhotographerController photographerController,
                    WorkshopController workshopController) {
        this.accessoryController = accessoryController;
        this.cameraController = cameraController;
        this.lensController = lensController;
        this.photographerController = photographerController;
        this.workshopController = workshopController;
    }

    @Override
    public void run(String... args) throws Exception {
        //Import Data
        //this.lensController.importLenses();
        //this.cameraController.importCameras();
        //this.photographerController.importPhotographers();
        //this.accessoryController.importAccessories();
        //this.workshopController.importWorkshops();

        //Export Data
        //this.photographerController.exportOrderedPhotographers();
        //this.photographerController.exportLandscapePhotographers();
        //this.photographerController.exportSameCameraPhotographers();
        //this.workshopController.exportWorkshopsByLocation();
    }
}
