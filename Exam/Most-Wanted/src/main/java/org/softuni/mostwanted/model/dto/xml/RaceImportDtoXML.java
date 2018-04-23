package org.softuni.mostwanted.model.dto.xml;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "race")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceImportDtoXML {
    @XmlElement(name = "laps")
    private int laps;

    @XmlElement(name = "district-name")
    @NotNull
    private String districtName;

    @XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<EntryImportDtoXML> entries;

    public RaceImportDtoXML() {
        this.entries = new ArrayList<>();
    }

    public int getLaps() {
        return laps;
    }

    public void setLaps(int laps) {
        this.laps = laps;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<EntryImportDtoXML> getEntries() {
        return entries;
    }

    public void setEntries(List<EntryImportDtoXML> entries) {
        this.entries = entries;
    }
}
