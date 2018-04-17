package app.model.dtos.view;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "photographers")
@XmlAccessorType(XmlAccessType.FIELD)
public class PhotographerWrapperView implements Serializable{
    @XmlElement(name = "photographer")
    private List<SameCameraPhotographerView> photographers;

    public PhotographerWrapperView() {
        this.photographers = new ArrayList<>();
    }

    public List<SameCameraPhotographerView> getPhotographers() {
        return photographers;
    }

    public void setPhotographers(List<SameCameraPhotographerView> photographers) {
        this.photographers = photographers;
    }
}
