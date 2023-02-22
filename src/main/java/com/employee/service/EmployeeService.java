package com.employee.service;

import java.util.List;

import com.employee.domain.Employee;

public interface EmployeeService {
	
	List<Employee> getEmployees();

	List<Employee> logWork(int id, int workDays) throws Exception;

	List<Employee> takeVacation(int id, float vacations) throws Exception;
}
