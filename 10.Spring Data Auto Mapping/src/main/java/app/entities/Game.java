package app.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true)
    @Length(min = 3, max = 100)
    @Pattern(regexp = "^[A-Z].+$")
    private String title;

    @Column(name = "trailer")
    @Length(min = 11, max = 11)
    private String trailer;

    @Column(name = "image_url")
    @Pattern(regexp = "^(http|https)://.*$")
    private String imageURL;

    @Column(name = "size")
    @Digits(integer = 5, fraction = 1)
    private BigDecimal size;

    @Column(name = "price")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal price;

    @Column(name = "description")
    @Length(min = 20)
    private String description;

    @Column(name = "release_date")
    private Date releaseDate;

    public Game() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        if (size.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Size must be positive!");
        }
        this.size = size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be positive!");
        }
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
