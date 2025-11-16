package com.kurban.Docker.Demo.app.Implementation;

import com.kurban.Docker.Demo.app.Entity.Employee;
import com.kurban.Docker.Demo.app.Repository.EmployeeRepository;
import com.kurban.Docker.Demo.app.Service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> allEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> employeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> updateEmployee(Long id, Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isEmpty()){
            return Optional.empty();
        }

        Employee existingEmployee = employeeOptional.get();
        existingEmployee.setName(employee.getName());
        existingEmployee.setTitle(employee.getTitle());
        existingEmployee.setSalary(employee.getSalary());

        return Optional.of(existingEmployee);
    }

    @Override
    public boolean deleteEmployee(Long id) {
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
