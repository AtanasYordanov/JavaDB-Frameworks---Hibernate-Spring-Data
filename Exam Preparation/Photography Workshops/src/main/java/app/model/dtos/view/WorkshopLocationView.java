package app.model.dtos.view;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "location")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopLocationView {
    @XmlAttribute
    private String name;

    @XmlElement(name = "workshop")
    List<WorkshopView> workshops;

    public WorkshopLocationView() {
        this.workshops = new ArrayList<>();
    }

    public List<WorkshopView> getWorkshops() {
        return workshops;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkshops(List<WorkshopView> workshops) {
        this.workshops = workshops;
    }
}
