package org.softuni.mostwanted.model.dto.json;

import com.google.gson.annotations.Expose;

public class TownExportDtoJSON {
    @Expose
    private String name;

    @Expose
    private Integer racers;

    public TownExportDtoJSON() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRacers() {
        return racers;
    }

    public void setRacers(Integer racers) {
        this.racers = racers;
    }
}
