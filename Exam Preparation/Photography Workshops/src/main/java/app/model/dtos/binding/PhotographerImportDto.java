package app.model.dtos.binding;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;

public class PhotographerImportDto implements Serializable {
    @Expose
    @NotNull
    private String firstName;

    @Expose
    @NotNull
    @Length(min = 2, max = 50)
    private String lastName;

    @Expose
    @Pattern(regexp = "^\\+\\d{1,3}/\\d{8,10}$")
    private String phone;

    @Expose
    private Set<Long> lenses;

    public PhotographerImportDto() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Long> getLenses() {
        return lenses;
    }

    public void setLenses(Set<Long> lenses) {
        this.lenses = lenses;
    }
}
