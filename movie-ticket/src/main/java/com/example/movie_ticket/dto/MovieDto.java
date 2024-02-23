package com.example.movie_ticket.dto;

import com.example.movie_ticket.model.Category;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class MovieDto implements Validator {
    private Long id;
    @NotBlank(message = "Không được để trống tên phim")
    private String title;
    @NotBlank(message = "Không được để trống mô tả")
    @Size(max = 10000)
    private String description;
    @NotBlank(message = "Không được để trống ngày công chiếu")
    private String releaseDate;
    private Category category;
    @NotBlank(message = "Không được để trống tên đạo diễn")
    private String director;
    @NotBlank(message = "Không được để trống link ảnh")
    private String avatar;
    @NotBlank(message = "Không được để trống link ảnh")
    private String banner;
    private int deleted;

    public MovieDto() {
    }

    public MovieDto(Long id, String title, String description, String releaseDate, Category category, String director, String avatar, String banner, int deleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.category = category;
        this.director = director;
        this.avatar = avatar;
        this.banner = banner;
        this.deleted = deleted;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Override
    public void validate(Object target, Errors errors) {
        MovieDto movieDto = (MovieDto) target;
        System.out.println(LocalDate.parse(movieDto.getReleaseDate()));
        System.out.println(LocalDate.now());
        if (LocalDate.now().isAfter(LocalDate.parse(movieDto.getReleaseDate()))){
            errors.rejectValue("releaseDate",null,"Không được chọn ngày quá khứ");
        }
    }
}
