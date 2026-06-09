package spring.code1.Service;
import spring.code1.EmployeeDTO;
import spring.code1.Entity.EmployeeEntity;

import spring.code1.Repo.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(ModelMapper modelMapper, EmployeeRepository employeeRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
    }


    public Optional<EmployeeDTO> getEmployee(Long employeeId) {
//        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId).orElse(null);
//        return EmployeeEntity
//                .map(employeeEntity1 -> modelMapper.map(employeeEntity1, EmployeeDTO.class);
        return employeeRepository.findById(employeeId)
                .map(employeeEntity -> modelMapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> employeeList = employeeRepository.findAll();

        return employeeList.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO saveEmployee(EmployeeDTO employee) {
        EmployeeEntity toSaveEntity = modelMapper.map(employee, EmployeeEntity.class);
        EmployeeEntity savedEntity =  employeeRepository.save(toSaveEntity);
        return modelMapper.map(savedEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateById(Long employeeId, EmployeeDTO employeeDTO) {

       isExists(employeeId);

        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(employeeId);

        EmployeeEntity savedEmployee = employeeRepository.save(employeeEntity);

        return modelMapper.map(savedEmployee, EmployeeDTO.class);
    }

    public void isExists(Long employeeId) {

        boolean exists = employeeRepository.existsById(employeeId);

        if (!exists) {
            throw new NoSuchElementException(
                    "Resource Not Found with id: " + employeeId
            );
        }
    }

    public boolean deleteEmployeeById(Long EmployeeId)
    {
        isExists(EmployeeId);

        employeeRepository.deleteById(EmployeeId);
        return true;
    }

    public EmployeeDTO updatePartialById(Long employeeId, Map<String, Object> updates) {
        isExists(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}