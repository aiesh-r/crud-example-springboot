package springboot2.crud.project.crudexamplespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot2.crud.project.crudexamplespringboot.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
