package app.model.dtos.binding;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "workshop")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopImportDto implements Serializable {
    @NotNull
    @XmlAttribute
    private String name;

    @XmlAttribute(name = "start-date")
    private Date startDate;

    @XmlAttribute(name = "end-date")
    private Date endDate;

    @NotNull
    @XmlAttribute
    private String location;

    @NotNull
    @XmlAttribute(name = "price")
    private BigDecimal pricePerParticipant;

    @NotNull
    @XmlElement
    private String trainer;

    @XmlElementWrapper(name = "participants")
    @XmlElement(name = "participant")
    private List<ParticipantDto> participantDtos;

    public WorkshopImportDto() {
        this.participantDtos = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getPricePerParticipant() {
        return pricePerParticipant;
    }

    public void setPricePerParticipant(BigDecimal pricePerParticipant) {
        this.pricePerParticipant = pricePerParticipant;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public List<ParticipantDto> getParticipantDtos() {
        return participantDtos;
    }

    public void setParticipantDtos(List<ParticipantDto> participantDtos) {
        this.participantDtos = participantDtos;
    }
}
