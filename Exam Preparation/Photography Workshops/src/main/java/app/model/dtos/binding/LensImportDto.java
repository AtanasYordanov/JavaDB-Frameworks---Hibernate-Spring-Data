package app.model.dtos.binding;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class LensImportDto implements Serializable {
    @Expose
    private String make;

    @Expose
    private Integer focalLength;

    @Expose
    private Double maxAperture;

    @Expose
    private String compatibleWith;

    public LensImportDto() {
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Integer getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(Integer focalLength) {
        this.focalLength = focalLength;
    }

    public Double getMaxAperture() {
        return maxAperture;
    }

    public void setMaxAperture(Double maxAperture) {
        this.maxAperture = maxAperture;
    }

    public String getCompatibleWith() {
        return compatibleWith;
    }

    public void setCompatibleWith(String compatibleWith) {
        this.compatibleWith = compatibleWith;
    }
}
