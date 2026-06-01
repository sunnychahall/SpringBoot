package spring.code1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @GetMapping("/Employee/{EmployeeId}")
    public EmployeeDTO employeeDetails(@PathVariable Long EmployeeId) {
        return new EmployeeDTO(
                EmployeeId,
                "sunny",
                "@cahahl",
                23,
                true
        );
    }
}