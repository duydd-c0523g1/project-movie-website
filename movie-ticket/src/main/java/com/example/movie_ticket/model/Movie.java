package com.example.movie_ticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.*;

@Entity(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(columnDefinition = "DATE")
    private String releaseDate;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    private String director;
    private String avatar;
    private String banner;
    @OneToMany(mappedBy = "movie")
    private Set<ShowTime> showTimes;
    private int deleted;

    public Movie() {
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

    public Set<ShowTime> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(Set<ShowTime> showTimes) {
        this.showTimes = showTimes;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public Map<String, List<ShowTime>> showTimesMap() {
        Map<String, List<ShowTime>> showTime = new HashMap<>();
        for (ShowTime valueSet : getShowTimes()) {
            if (!showTime.containsKey(valueSet.getShowDate())) {
                List<ShowTime> timeRanges = new ArrayList<>();
                timeRanges.add(valueSet);
                showTime.put(valueSet.getShowDate(), timeRanges);
            } else {
                showTime.get(valueSet.getShowDate()).add(valueSet);
            }
        }
        return showTime;
    }
}
