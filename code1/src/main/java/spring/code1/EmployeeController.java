package spring.code1;

import org.springframework.web.bind.annotation.*;
import spring.code1.Repo.EmployeeRepository;
import spring.code1.Entity.EmployeeEntity;
import java.util.List;
import spring.code1.Service.EmployeeService;

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

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/{EmployeeId}")

    public EmployeeDTO getEmployee(@PathVariable Long EmployeeId) {
        return employeeService.getEmployee(EmployeeId);
    }


    @PostMapping
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employee) {
        return employeeService.saveEmployee(employee);
    }



}