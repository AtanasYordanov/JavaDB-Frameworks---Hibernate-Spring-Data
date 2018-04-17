package app.services.impl;

import app.constants.Messages;
import app.io.Writer;
import app.model.dtos.binding.PhotographerImportDto;
import app.model.dtos.view.LandscapePhotographerView;
import app.model.dtos.view.PhotographerView;
import app.model.dtos.view.PhotographerWrapperView;
import app.model.dtos.view.SameCameraPhotographerView;
import app.model.entities.BasicCamera;
import app.model.entities.Lens;
import app.model.entities.Photographer;
import app.repositories.CameraRepository;
import app.repositories.LensRepository;
import app.repositories.PhotographerRepository;
import app.services.api.PhotographerService;
import app.utils.mapper.MapperConverter;
import app.utils.validation.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class PhotographerServiceImpl implements PhotographerService {

    private PhotographerRepository photographerRepository;
    private CameraRepository cameraRepository;
    private LensRepository lensRepository;
    private MapperConverter mapper;
    private Writer writer;

    @Autowired
    public PhotographerServiceImpl(PhotographerRepository photographerRepository, CameraRepository cameraRepository
            , LensRepository lensRepository, MapperConverter mapper, Writer writer) {
        this.photographerRepository = photographerRepository;
        this.cameraRepository = cameraRepository;
        this.lensRepository = lensRepository;
        this.mapper = mapper;
        this.writer = writer;
    }

    @Override
    public void save(Photographer photographer) {
        this.photographerRepository.save(photographer);
    }

    @Override
    public void saveAll(PhotographerImportDto[] photographerDtos) {
        Random random = new Random();
        List<BasicCamera> cameras = this.cameraRepository.findAll();
        for (PhotographerImportDto photographerDto : photographerDtos) {
            if (ValidationUtil.isValid(photographerDto)) {
                Photographer photographer = this.mapper.convert(photographerDto, Photographer.class);
                int randomCameraIndex = random.nextInt(cameras.size());
                photographer.setPrimaryCamera(cameras.get(randomCameraIndex));
                randomCameraIndex = random.nextInt(cameras.size());
                photographer.setSecondaryCamera(cameras.get(randomCameraIndex));
                for (Long lensId : photographerDto.getLenses()) {
                    Lens lens = this.lensRepository.findById(lensId).orElse(null);
                    if (lens != null && lens.getCompatibleWith() != null
                            && (lens.getCompatibleWith().equals(photographer.getPrimaryCamera().getMake())
                            || lens.getCompatibleWith().equals(photographer.getSecondaryCamera().getMake()))) {
                        photographer.getLenses().add(lens);
                        lens.setOwner(photographer);
                    }
                }
                this.save(photographer);
                this.writer.println(Messages.SUCCESSFULLY_IMPORTED_PHOTOGRAPHER_MESSAGE, photographer.getFirstName(),
                        photographer.getLastName(), photographer.getLenses().size());
            }
        }
    }

    @Override
    public List<PhotographerView> getOrderedPhotographers() {
        List<Photographer> photographers = this.photographerRepository.findAllByOrderByFirstNameAscLastNameDesc();
        PhotographerView[] photographerViews = this.mapper.convert(photographers, PhotographerView[].class);
        return Arrays.asList(photographerViews);
    }

    @Override
    public List<LandscapePhotographerView> getLandscapePhotographers() {
        List<Photographer> photographers = this.photographerRepository.getLandscapePhotographers();
        List<LandscapePhotographerView> output = new ArrayList<>();
        for (Photographer photographer : photographers) {
            LandscapePhotographerView photographerView = new LandscapePhotographerView();
            photographerView.setFirstName(photographer.getFirstName());
            photographerView.setLastName(photographer.getLastName());
            photographerView.setCameraMake(photographer.getPrimaryCamera().getMake());
            photographerView.setLensesCount(photographer.getLenses().size());
            output.add(photographerView);
        }
        return output;
    }

    @Override
    public PhotographerWrapperView getSameCameraPhotographers() {
        List<Photographer> photographers = this.photographerRepository.getSameCameraPhotographers();
        PhotographerWrapperView photographersView = new PhotographerWrapperView();
        for (Photographer photographer : photographers) {
            SameCameraPhotographerView photographerView = new SameCameraPhotographerView();
            photographerView.setName(photographer.getFirstName() + " " + photographer.getLastName());
            photographerView.setPrimaryCamera(photographer.getPrimaryCamera().getMake()
                    + " " + photographer.getPrimaryCamera().getModel());
            for (Lens lens : photographer.getLenses()) {
                String lensDetails = String.format("%s %dmm f%.1f", lens.getMake()
                        , lens.getFocalLength(), lens.getMaxAperture());
                photographerView.getLensesDetails().add(lensDetails);
            }
            photographersView.getPhotographers().add(photographerView);
        }
        return photographersView;
    }
}
