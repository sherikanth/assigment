package com.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.employee.domain.Employee;
import com.employee.domain.HourlyEmployee;
import com.employee.domain.Manager;
import com.employee.domain.SalariedEmployee;
import com.employee.enums.EmployeeType;

import jakarta.annotation.PostConstruct;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static List<Employee> employees;

	@PostConstruct
	private void createEmployees() {
		employees = new ArrayList<>();
		employees.add(getEmployee(1, "John", EmployeeType.HOURLY));
		employees.add(getEmployee(2, "Chris", EmployeeType.HOURLY));
		employees.add(getEmployee(3, "Symond", EmployeeType.HOURLY));
		employees.add(getEmployee(4, "Robert", EmployeeType.HOURLY));
		employees.add(getEmployee(5, "Stephen", EmployeeType.SALARIED));
		employees.add(getEmployee(6, "Beryl", EmployeeType.SALARIED));
		employees.add(getEmployee(7, "Medimi", EmployeeType.SALARIED));
		employees.add(getEmployee(8, "Lazaro", EmployeeType.MANAGER));
		employees.add(getEmployee(9, "James", EmployeeType.MANAGER));
		employees.add(getEmployee(10, "Harvard", EmployeeType.MANAGER));
	}

	@Override
	public List<Employee> getEmployees() {
		return employees;
	}

	@Override
	public List<Employee> logWork(int id, int workDays) throws Exception {
		Optional<Employee> emp = employees.stream().filter(employee -> employee.getId() == id).findFirst();
		if (emp.isPresent()) {
			emp.get().logWork(workDays);
		} else {
			throw new RuntimeException("Employee not found");
		}
		return employees;
	}

	@Override
	public List<Employee> takeVacation(int id, float vacations) throws Exception {
		Optional<Employee> emp = employees.stream().filter(employee -> employee.getId() == id).findFirst();
		if (emp.isPresent()) {
			emp.get().takeVacation(vacations);
		} else {
			throw new RuntimeException("Employee not found");
		}
		return employees;
	}

	private Employee getEmployee(int id, String name, EmployeeType type) {
		Employee employee = null;
		switch (type) {
		case HOURLY:
			employee = new HourlyEmployee();
			employee.setType(EmployeeType.HOURLY);
			break;
		case SALARIED:
			employee = new SalariedEmployee();
			employee.setType(EmployeeType.SALARIED);
			break;
		case MANAGER:
			employee = new Manager();
			employee.setType(EmployeeType.MANAGER);
			break;
		}
		employee.setId(id);
		employee.setName(name);
		return employee;
	}

}