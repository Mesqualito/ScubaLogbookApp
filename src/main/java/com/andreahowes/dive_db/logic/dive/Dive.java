package com.andreahowes.dive_db.logic.dive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Dive {
    private int id;
    private String user;

    @JsonProperty("date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String location;
    private Double durationInMinutes;
    private Double maxDepthInMeters;
    private String waterConditions;
    private boolean safetyStop;

    public Dive() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Double durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Double getMaxDepthInMeters() {
        return maxDepthInMeters;
    }

    public void setMaxDepthInMeters(Double maxDepthInMeters) {
        this.maxDepthInMeters = maxDepthInMeters;
    }

    public String getWaterConditions() {
        return waterConditions;
    }

    public void setWaterConditions(String waterConditions) {
        this.waterConditions = waterConditions;
    }

    public Boolean getSafetyStop() {
        return safetyStop;
    }

    public void setSafetyStop(Boolean safetyStop) {
        this.safetyStop = safetyStop;
    }
}
