package spring.code1;

import jakarta.validation.Valid;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.code1.Repo.EmployeeRepository;
import spring.code1.Entity.EmployeeEntity;
import java.util.List;
import java.lang.Object;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import spring.code1.Advices.GlobalExceptionHandler;
import spring.code1.Service.EmployeeService;



@CrossOrigin(origins = "http://127.0.0.1:3000")
@RestController
@RequestMapping(path = "/Employee")
public class EmployeeController {

//    @GetMapping("/Employee/{EmployeeId}")
//    public EmployeeDTO employeeDetails(@PathVariable Long EmployeeId) {
//        return new EmployeeDTO(
//                EmployeeId,
//                "sunny",git a
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
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {

        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping(path = "/{EmployeeId}")

    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable Long EmployeeId) {
       Optional<EmployeeDTO> employeeDTO =  employeeService.getEmployee(EmployeeId);
       return employeeDTO
               .map(employeeDTO1 -> ResponseEntity.ok(employeeDTO1))
               .orElseThrow(() ->
                       new NoSuchElementException(
                               "Resource Not Found with id: " + EmployeeId
                       )
               );
    }


    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody @Valid EmployeeDTO employee) {
        EmployeeDTO savedEmployee = employeeService.saveEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{EmployeeId}")
    public ResponseEntity<EmployeeDTO> updateById(@RequestBody EmployeeDTO employeeDTO, @PathVariable Long EmployeeId)
    {
        return ResponseEntity.ok(employeeService.updateById(EmployeeId, employeeDTO));
    }

    @DeleteMapping(path = "/{EmployeeId}")

    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long EmployeeId)
    {
        boolean deleted = employeeService.deleteEmployeeById(EmployeeId);
        if (!deleted) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(deleted);
    }


    @PatchMapping(path = "/{EmployeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialById(@RequestBody Map<String, Object> updates, @PathVariable Long EmployeeId)
    {
        EmployeeDTO employeeDTO = employeeService.updatePartialById(EmployeeId, updates);
        if (employeeDTO == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(employeeDTO);
    }
}


