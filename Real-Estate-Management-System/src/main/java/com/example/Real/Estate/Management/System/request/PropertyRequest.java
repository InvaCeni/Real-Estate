package com.example.Real.Estate.Management.System.request;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PropertyRequest implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private String location;

    @NotNull
    private Double price;

    @NotNull
    private String type;

    @NotNull
    private Long agentId;

}
