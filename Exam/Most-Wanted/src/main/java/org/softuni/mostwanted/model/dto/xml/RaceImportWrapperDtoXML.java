package org.softuni.mostwanted.model.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "races")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportWrapperDtoXML {
    @XmlElement(name = "race")
    private List<RaceImportDtoXML> races;

    public RaceImportWrapperDtoXML() {
        this.races = new ArrayList<>();
    }

    public List<RaceImportDtoXML> getRaces() {
        return races;
    }

    public void setRaces(List<RaceImportDtoXML> races) {
        this.races = races;
    }
}
