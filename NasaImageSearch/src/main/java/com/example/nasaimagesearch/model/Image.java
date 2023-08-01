package com.example.nasaimagesearch.model;

// Class to represent an image object
public class Image {
    // Fields to store image details
    private String nasaId;
    private String title;
    private String description;
    private String href;

    // Constructors, getters, and setters
    public Image(String nasaId, String title, String description, String href) {
        this.nasaId = nasaId;
        this.title = title;
        this.description = description;
        this.href = href;
    }

    public String getNasaId() {
        return nasaId;
    }

    public void setNasaId(String nasaId) {
        this.nasaId = nasaId;
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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    // Override toString() method for debugging purposes
    @Override
    public String toString() {
        return "Image{" +
                "nasaId='" + nasaId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
