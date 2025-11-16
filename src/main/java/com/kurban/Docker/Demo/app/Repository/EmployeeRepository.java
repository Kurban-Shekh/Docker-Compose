package com.kurban.Docker.Demo.app.Repository;

import com.kurban.Docker.Demo.app.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
