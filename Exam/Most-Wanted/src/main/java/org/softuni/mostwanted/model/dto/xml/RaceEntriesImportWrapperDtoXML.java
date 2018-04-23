package org.softuni.mostwanted.model.dto.xml;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "race-entries")
@XmlAccessorType(XmlAccessType.FIELD)
public class RaceEntriesImportWrapperDtoXML {
    @XmlElement(name = "race-entry")
    private List<RaceEntryImportDtoXML> raceEntries;

    public RaceEntriesImportWrapperDtoXML() {
        this.raceEntries = new ArrayList<>();
    }

    public List<RaceEntryImportDtoXML> getRaceEntries() {
        return raceEntries;
    }

    public void setRaceEntries(List<RaceEntryImportDtoXML> raceEntries) {
        this.raceEntries = raceEntries;
    }
}
