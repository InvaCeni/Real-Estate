package com.example.Real.Estate.Management.System.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private Double price;
    private String type;
    private Long agentId;

    private String description;
    private String status;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User agent;

    public enum PropertyCategory {
        APARTAMENT,
        VILLA,
        COMMERCIAL,
        LAND
    }
}