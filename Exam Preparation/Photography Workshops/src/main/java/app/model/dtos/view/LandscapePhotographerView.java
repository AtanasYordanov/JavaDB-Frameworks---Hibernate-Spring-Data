package app.model.dtos.view;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class LandscapePhotographerView implements Serializable {
    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private String cameraMake;

    @Expose
    private Integer lensesCount;

    public LandscapePhotographerView() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCameraMake() {
        return cameraMake;
    }

    public void setCameraMake(String cameraMake) {
        this.cameraMake = cameraMake;
    }

    public Integer getLensesCount() {
        return lensesCount;
    }

    public void setLensesCount(Integer lensesCount) {
        this.lensesCount = lensesCount;
    }
}
