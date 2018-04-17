package app.model.dtos.view;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "locations")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopLocationWrapperView {
    @XmlElement(name = "location")
    private List<WorkshopLocationView> locations;

    public WorkshopLocationWrapperView() {
        this.locations = new ArrayList<>();
    }

    public List<WorkshopLocationView> getLocations() {
        return locations;
    }

    public void setLocations(List<WorkshopLocationView> locations) {
        this.locations = locations;
    }
}
