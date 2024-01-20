package com.app.dto;

import com.app.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class CourseDto {
    @NotBlank
    private String name;
    @NotNull
    private Category category;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    private Double fees;
}
