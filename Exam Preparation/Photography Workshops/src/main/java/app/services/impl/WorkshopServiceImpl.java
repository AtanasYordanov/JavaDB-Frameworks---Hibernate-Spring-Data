package app.services.impl;

import app.constants.Messages;
import app.io.Writer;
import app.model.dtos.binding.ParticipantDto;
import app.model.dtos.binding.WorkshopImportDto;
import app.model.dtos.view.ParticipantsView;
import app.model.dtos.view.WorkshopLocationView;
import app.model.dtos.view.WorkshopLocationWrapperView;
import app.model.dtos.view.WorkshopView;
import app.model.entities.Photographer;
import app.model.entities.Workshop;
import app.repositories.PhotographerRepository;
import app.repositories.WorkshopRepository;
import app.services.api.WorkshopService;
import app.utils.mapper.MapperConverter;
import app.utils.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkshopServiceImpl implements WorkshopService {

    private WorkshopRepository workshopRepository;
    private PhotographerRepository photographerRepository;
    private Writer writer;
    private MapperConverter mapper;

    @Autowired
    public WorkshopServiceImpl(WorkshopRepository workshopRepository
            , PhotographerRepository photographerRepository, Writer writer, MapperConverter mapper) {
        this.workshopRepository = workshopRepository;
        this.photographerRepository = photographerRepository;
        this.writer = writer;
        this.mapper = mapper;
    }

    @Override
    public void save(Workshop workshop) {
        this.workshopRepository.save(workshop);
    }

    @Override
    public void saveAll(List<WorkshopImportDto> workshops) {
        for (WorkshopImportDto workshopDto : workshops) {
            if (ValidationUtil.isValid(workshopDto) && workshopDto.getTrainer() != null) {
                Workshop workshop = this.mapper.convert(workshopDto, Workshop.class);
                String[] names = workshopDto.getTrainer().split("\\s+");
                Photographer trainer = this.photographerRepository.findByFirstNameAndLastName(names[0], names[1]);
                workshop.setTrainer(trainer);
                for (ParticipantDto participantDto : workshopDto.getParticipantDtos()) {
                    Photographer participant = this.photographerRepository
                            .findByFirstNameAndLastName(participantDto.getFirstName(), participantDto.getLastName());
                    workshop.getParticipants().add(participant);
                }
                this.save(workshop);
                this.writer.println(Messages.SUCCESSFULLY_IMPORTED_WORKSHOP_MESSAGE);
            } else {
                this.writer.println(Messages.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public WorkshopLocationWrapperView getWorkshopsByLocation() {
        List<Workshop> workshops = this.workshopRepository.getWorkshopsWithMoreThanFiveParticipants();
        WorkshopLocationWrapperView workshopsView = new WorkshopLocationWrapperView();
        workshops.stream()
                .collect(Collectors.groupingBy(Workshop::getLocation))
                .forEach((key, value) -> {
                    WorkshopLocationView locationView = new WorkshopLocationView();
                    locationView.setName(key);
                    for (Workshop workshop : value) {
                        WorkshopView workshopView = new WorkshopView();
                        workshopView.setName(workshop.getName());
                        BigDecimal totalProfit = BigDecimal.valueOf(workshop.getParticipants().size())
                                .multiply(workshop.getPricePerParticipant()).multiply(BigDecimal.valueOf(0.8));
                        workshopView.setTotalProfit(totalProfit);
                        ParticipantsView participantsView = new ParticipantsView();
                        for (Photographer participant : workshop.getParticipants()) {
                            String participantName = participant.getFirstName() + " " + participant.getLastName();
                            participantsView.getParticipants().add(participantName);
                        }
                        participantsView.setCount(participantsView.getParticipants().size());
                        workshopView.setParticipants(participantsView);
                        locationView.getWorkshops().add(workshopView);
                    }
                    workshopsView.getLocations().add(locationView);
                });
        return workshopsView;
    }
}
