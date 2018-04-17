package app.model.dtos.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "accessories")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccessoryWrapperDto {
    @XmlElement(name = "accessory")
    private List<AccessoryImportDto> accessories;

    public AccessoryWrapperDto() {
    }

    public List<AccessoryImportDto> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<AccessoryImportDto> accessories) {
        this.accessories = accessories;
    }
}
