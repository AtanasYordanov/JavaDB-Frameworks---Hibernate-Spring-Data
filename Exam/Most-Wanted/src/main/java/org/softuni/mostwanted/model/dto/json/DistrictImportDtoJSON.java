package org.softuni.mostwanted.model.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class DistrictImportDtoJSON {
    @Expose
    @NotNull
    private String name;

    @Expose
    @NotNull
    private String townName;

    public DistrictImportDtoJSON() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
