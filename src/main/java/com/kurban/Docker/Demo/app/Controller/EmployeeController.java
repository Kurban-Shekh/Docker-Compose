package com.kurban.Docker.Demo.app.Controller;

import com.kurban.Docker.Demo.app.Entity.Employee;
import com.kurban.Docker.Demo.app.Service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        return new ResponseEntity<>(employeeService.allEmployees(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Optional<Employee> employeeOptional = employeeService.employeeById(id);
        if(employeeOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(employeeOptional.get(),HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.saveEmployee(employee),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
        Optional<Employee> employeeOptional = employeeService.updateEmployee(id,employee);
        if(employeeOptional.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(employeeOptional.get(),HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
        if(employeeService.deleteEmployee(id)){
            return new ResponseEntity<>("Employee has been removed successfully.",HttpStatus.OK);
        }else{
            return new ResponseEntity<>("We couldn't find the employee in our database.",HttpStatus.NOT_FOUND);
        }
    }
}
