package app.model.dtos.view;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "photographer")
@XmlAccessorType(XmlAccessType.FIELD)
public class SameCameraPhotographerView implements Serializable {
    @XmlAttribute
    private String name;

    @XmlAttribute(name = "primary-camera")
    private String primaryCamera;

    @XmlElementWrapper(name = "lenses")
    @XmlElement(name = "lens")
    private List<String> lensesDetails;

    public SameCameraPhotographerView() {
        this.lensesDetails = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimaryCamera() {
        return primaryCamera;
    }

    public void setPrimaryCamera(String primaryCamera) {
        this.primaryCamera = primaryCamera;
    }

    public List<String> getLensesDetails() {
        return lensesDetails;
    }

    public void setLensesDetails(List<String> lensesDetails) {
        this.lensesDetails = lensesDetails;
    }
}
