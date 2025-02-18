package com.example.Real.Estate.Management.System.request;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@Getter
@Data
public class PropertyRequest implements Serializable {

    @NotNull
        private String name;

    @NotNull
        private String location;

    @NotNull
        private double price;

    @NotNull
        private Long agentId;

    @NotNull
        private String description;

    @NotNull
        private String status;

    @NotNull
        private String imageUrl;

    @NotNull
        private String category;

        public PropertyRequest(String name, String location, double price, Long agentId,
                               String description, String status, String imageUrl, String category) {
            this.name = name;
            this.location = location;
            this.price = price;
            this.agentId = agentId;
            this.description = description;
            this.status = status;
            this.imageUrl = imageUrl;
            this.category = category;
        }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public double getPrice() {
        return price;
    }

    public Long getAgentId() {
        return agentId;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}

