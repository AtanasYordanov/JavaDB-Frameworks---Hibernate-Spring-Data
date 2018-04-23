package org.softuni.mostwanted.model.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "most-wanted")
@XmlAccessorType(XmlAccessType.FIELD)
public class MostWantedExportWrapperDtoXML {
    @XmlElement(name = "racer")
    private MostWantedExportDtoXML racer;

    public MostWantedExportWrapperDtoXML() {
    }

    public MostWantedExportDtoXML getRacer() {
        return racer;
    }

    public void setRacer(MostWantedExportDtoXML racer) {
        this.racer = racer;
    }
}
