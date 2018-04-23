package app.retake.services.impl;

import app.retake.domain.dto.*;
import app.retake.domain.models.Animal;
import app.retake.domain.models.AnimalAid;
import app.retake.domain.models.Procedure;
import app.retake.domain.models.Vet;
import app.retake.parser.interfaces.ModelParser;
import app.retake.repositories.ProcedureRepository;
import app.retake.services.api.AnimalAidService;
import app.retake.services.api.AnimalService;
import app.retake.services.api.ProcedureService;
import app.retake.services.api.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
@Transactional
public class ProcedureServiceImpl implements ProcedureService {

    private ProcedureRepository procedureRepository;
    private AnimalService animalService;
    private VetService vetService;
    private AnimalAidService animalAidService;
    private ModelParser mapper;

    @Autowired
    public ProcedureServiceImpl(ProcedureRepository procedureRepository
            , AnimalService animalService, VetService vetService, AnimalAidService animalAidService, ModelParser mapper) {
        this.procedureRepository = procedureRepository;
        this.animalService = animalService;
        this.vetService = vetService;
        this.animalAidService = animalAidService;
        this.mapper = mapper;
    }

    @Override
    public void create(ProcedureXMLImportDTO dto) throws ParseException {
        Vet vet = this.vetService.getByName(dto.getVet());
        Animal animal = this.animalService.getByPassportSerialNumber(dto.getAnimal());
        if (vet == null && animal == null){
            throw new IllegalArgumentException();
        }else{
            Procedure procedure = new Procedure();
            for (ProcedureAnimalAidXMLImportDTO animalAidDto : dto.getAnimalAids()) {
                AnimalAid animalAid = this.animalAidService.getOneByName(animalAidDto.getName());
                if (animalAid == null){
                    throw new IllegalArgumentException();
                }
                procedure.getServices().add(animalAid);
            }
            procedure.setAnimal(animal);
            procedure.setVet(vet);
            procedure.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(dto.getDate()));
            this.procedureRepository.saveAndFlush(procedure);
        }
    }

    @Override
    public ProcedureWrapperXMLExportDTO exportProcedures() {
        ProcedureWrapperXMLExportDTO procedureDtos = new ProcedureWrapperXMLExportDTO();
        List<Procedure> procedures = this.procedureRepository.findAll();
        for (Procedure procedure : procedures) {
            ProcedureXMLExportDTO procedureDto = new ProcedureXMLExportDTO();
            procedureDto.setOwnerPhone(procedure.getAnimal().getPassport().getOwnerPhoneNumber());
            procedureDto.setAnimalPassport(procedure.getAnimal().getPassport().getSerialNumber());
            for (AnimalAid animalAid : procedure.getServices()) {
                ProcedureAnimalAidXMLExportDTO animalAidDto =
                        this.mapper.convert(animalAid, ProcedureAnimalAidXMLExportDTO.class);
                procedureDto.getAnimalAids().add(animalAidDto);
            }
            procedureDtos.getProcedures().add(procedureDto);
        }
        return procedureDtos;
    }
}

