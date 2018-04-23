package app.exam.domain.dto.json;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeJSONImportDTO  {
    @Expose
    @NotNull
    @Size(min = 3, max = 30)
    private String name;

    @Expose
    @NotNull
    @Min(15)
    @Max(80)
    private Integer age;

    @Expose
    @NotNull
    @Size(min = 3, max = 30)
    private String position;

    public EmployeeJSONImportDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
