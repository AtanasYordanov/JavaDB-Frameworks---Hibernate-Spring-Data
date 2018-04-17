package app.model.dtos.view;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "workshop")
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkshopView {
    @XmlAttribute
    private String name;

    @XmlAttribute(name = "total-profit")
    private BigDecimal totalProfit;

    @XmlElement(name = "participants")
    private ParticipantsView participants;

    public WorkshopView() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(BigDecimal totalProfit) {
        this.totalProfit = totalProfit;
    }

    public ParticipantsView getParticipants() {
        return participants;
    }

    public void setParticipants(ParticipantsView participants) {
        this.participants = participants;
    }
}
