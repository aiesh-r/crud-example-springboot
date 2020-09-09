package springboot2.crud.project.crudexamplespringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springboot2.crud.project.crudexamplespringboot.model.Employee;
import springboot2.crud.project.crudexamplespringboot.repository.EmployeeRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1/employees")
public class EmployeeController {
    @Autowired
    public EmployeeRepository employeeRepository;

    @GetMapping(value = "/get")
    public List<Employee> findEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Employee>> findById(@PathVariable(value = "id") Long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return ResponseEntity.ok().body(employee);
    }

    @PostMapping(value = "/ceateEmp")
    public void createEmployee(@Validated @RequestBody Employee employee){
        employeeRepository.save(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @Validated @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        employee.setEmailId(employeeDetails.getEmailId());
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getFirstName());
        final Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok().body(updatedEmployee);
    }

    @DeleteMapping(value = "/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") long employeeId){
        Employee employee = employeeRepository.findById(employeeId).orElse(null);
        employeeRepository.delete(employee);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;

    }

}
