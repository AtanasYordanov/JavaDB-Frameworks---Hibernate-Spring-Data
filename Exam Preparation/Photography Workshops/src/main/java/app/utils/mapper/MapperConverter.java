package app.utils.mapper;

import app.model.dtos.binding.CameraImportDto;
import app.model.entities.BasicCamera;
import app.model.entities.DSLRCamera;
import app.model.entities.MirrorlessCamera;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperConverter {

    private ModelMapper modelMapper;

    public MapperConverter() {
        this.modelMapper = new ModelMapper();
    }

    public <S, D> D convert(S source, Class<D> destinationClass) {
        return this.modelMapper.map(source, destinationClass);
    }

    public BasicCamera convertCamera(CameraImportDto source) {
        if (source == null){
            return null;
        }
        switch (source.getType()) {
            case "DSLR":
                return this.modelMapper.map(source, DSLRCamera.class);
            case "Mirrorless":
                return this.modelMapper.map(source, MirrorlessCamera.class);
            default:
                throw new IllegalArgumentException("No such camera type!");
        }
    }
}