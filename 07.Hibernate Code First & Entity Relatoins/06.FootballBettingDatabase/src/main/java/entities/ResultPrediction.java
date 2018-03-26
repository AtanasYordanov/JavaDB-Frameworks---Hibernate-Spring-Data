package entities;

import javax.persistence.*;

@Entity
@Table(name = "result_predictions")
public class ResultPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prediction")
    private String prediction;

    public ResultPrediction() {
    }

    public ResultPrediction(String prediction) {
        this.prediction = prediction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrediction() {
        return prediction;
    }

    public void setPrediction(String prediction) {
        this.prediction = prediction;
    }
}
