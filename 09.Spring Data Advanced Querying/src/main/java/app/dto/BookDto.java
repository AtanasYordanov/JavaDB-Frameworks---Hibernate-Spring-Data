package app.dto;

import app.enums.AgeRestriction;
import app.enums.EditionType;

import java.math.BigDecimal;

public class BookDto implements ReducedBook{
    private String title;
    private EditionType editionType;
    private AgeRestriction ageRestriction;
    private BigDecimal price;

    public BookDto() {
    }

    public BookDto(String title, EditionType editionType, AgeRestriction ageRestriction, BigDecimal price) {
        this.title = title;
        this.editionType = editionType;
        this.ageRestriction = ageRestriction;
        this.price = price;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public EditionType getEditionType() {
        return this.editionType;
    }

    @Override
    public AgeRestriction getAgeRestriction() {
        return this.ageRestriction;
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }
}
