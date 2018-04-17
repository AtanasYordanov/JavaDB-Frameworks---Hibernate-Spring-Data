package app.services.impl;

import app.constants.Messages;
import app.io.Writer;
import app.model.dtos.binding.LensImportDto;
import app.model.entities.Lens;
import app.repositories.LensRepository;
import app.services.api.LensService;
import app.utils.mapper.MapperConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class LensServiceImpl implements LensService{

    private LensRepository lensRepository;
    private MapperConverter mapper;
    private Writer writer;

    @Autowired
    public LensServiceImpl(LensRepository lensRepository, MapperConverter mapperConverter, Writer writer) {
        this.lensRepository = lensRepository;
        this.mapper = mapperConverter;
        this.writer = writer;
    }

    @Override
    public void save(Lens lens) {
        this.lensRepository.save(lens);
    }

    @Override
    public void saveAll(LensImportDto[] lensDtos) {
        Lens[] lenses = this.mapper.convert(lensDtos, Lens[].class);
        for (Lens lens : lenses) {
            this.save(lens);
            this.writer.println(Messages.SUCCESSFULLY_IMPORTED_LENS_MESSAGE, lens.getMake(),
                    lens.getFocalLength(), lens.getMaxAperture());
        }
    }

    @Override
    public Lens findById(Long id) {
        return this.lensRepository.findById(id).orElse(null);
    }
}
