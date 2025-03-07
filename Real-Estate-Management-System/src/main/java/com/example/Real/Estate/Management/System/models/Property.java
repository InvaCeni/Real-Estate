package com.example.Real.Estate.Management.System.models;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "properties")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name is required")
    private String name;

    @Column(nullable = false, length = 1000)
    @NotBlank(message = "Description is required")
    private String description;

    @Column(nullable = false)
    @NotBlank(message = "Address is required")
    private String address;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(nullable = false)
    @Min(value = 1, message = "Bathrooms must be at least 1")
    private Integer bathrooms;

    @Column(nullable = false)
    @Min(value = 1, message = "Bedrooms must be at least 1")
    private Integer bedrooms;

    @Column(nullable = false)
    private Boolean furnished;

    @Column(nullable = false)
    private Boolean parking;

    @ElementCollection
    @CollectionTable(name = "listing_image_urls", joinColumns = @JoinColumn(name = "property_id"))
    @OrderColumn(name = "image_order")
    @Column(name = "image_url")
    private List<String> imageUrls;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public Property() {
        this.name = "Default Name";
        this.description = "Default Description";
        this.address = "123, Bouzereah 44521";
        this.price = 0.0;
        this.bathrooms = 0;
        this.bedrooms = 0;
        this.furnished = false;
        this.parking = false;
        this.imageUrls = List.of("https://i.imgur.com/n6B1Fuw.jpg", "https://i.imgur.com/n6B1Fuw.jpg");
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    public Integer getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(Integer bathrooms) {
        this.bathrooms = bathrooms;
    }

    public Integer getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(Integer bedrooms) {
        this.bedrooms = bedrooms;
    }

    public Boolean getFurnished() {
        return furnished;
    }

    public void setFurnished(Boolean furnished) {
        this.furnished = furnished;
    }

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }


    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", bathrooms=" + bathrooms +
                ", bedrooms=" + bedrooms +
                ", furnished=" + furnished +
                ", parking=" + parking +
                ", imageUrls=" + imageUrls +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}