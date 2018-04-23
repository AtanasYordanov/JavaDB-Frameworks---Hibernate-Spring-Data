package app.exam.domain.dto.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "category")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryExportDTO {
    @XmlElement
    private String name;

    @XmlElement(name = "most-popular-item")
    private MostPopularItemDTO mostPopularItem;

    public CategoryExportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MostPopularItemDTO getMostPopularItem() {
        return mostPopularItem;
    }

    public void setMostPopularItem(MostPopularItemDTO mostPopularItem) {
        this.mostPopularItem = mostPopularItem;
    }
}
