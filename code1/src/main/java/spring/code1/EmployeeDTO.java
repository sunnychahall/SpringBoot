package spring.code1;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;
import spring.code1.Annotation.EmployeeRoleValidation;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {


    private Long id;
    @NotBlank(message = "cannot be blank")
    @Size(min = 3, max = 15, message = "Number of characters should be in range")
    private String name;

    @NotBlank(message = "cannot be blank")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "cannot be null")
    @Max(value = 60, message = "Age cannot be greater than 60")
    @Min(value = 18, message = "Age should be greater than 18")
    private int age;

    @EmployeeRoleValidation
    private String role;

    //    @JsonProperty("isActive")
    @AssertTrue(message = "Employee should be active")
    private boolean isActive;

    private double salary;

}