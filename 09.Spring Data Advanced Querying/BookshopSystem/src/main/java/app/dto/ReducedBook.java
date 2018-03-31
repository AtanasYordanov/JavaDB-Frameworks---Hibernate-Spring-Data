package app.dto;

import app.enums.AgeRestriction;
import app.enums.EditionType;

import java.math.BigDecimal;

public interface ReducedBook {
    String getTitle();

    EditionType getEditionType();

    AgeRestriction getAgeRestriction();

    BigDecimal getPrice();
}
