package app.model.dtos.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "workshops")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopWrapperDto implements Serializable {
    @XmlElement(name = "workshop")
    private List<WorkshopImportDto> workshops;

    public WorkshopWrapperDto() {
    }

    public List<WorkshopImportDto> getWorkshops() {
        return workshops;
    }

    public void setWorkshops(List<WorkshopImportDto> workshops) {
        this.workshops = workshops;
    }
}
