package spring.code1;

import org.springframework.web.bind.annotation.*;
import spring.code1.Repo.EmployeeRepository;
import spring.code1.Entity.EmployeeEntity;
import java.util.List;

@RestController
@RequestMapping(path = "/Employee")
public class EmployeeController {

//    @GetMapping("/Employee/{EmployeeId}")
//    public EmployeeDTO employeeDetails(@PathVariable Long EmployeeId) {
//        return new EmployeeDTO(
//                EmployeeId,
//                "sunny",
//                "@cahahl",
//                23,
//                true
//        );
//    }

    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping(path = "/{EmployeeId}")

    public EmployeeEntity getEmployee(@PathVariable Long EmployeeId) {
        return employeeRepository.findById(EmployeeId).orElse(null);
    }


    @PostMapping
    public EmployeeEntity saveEmployee(@RequestBody EmployeeEntity employee) {
        return employeeRepository.save(employee);
    }


}