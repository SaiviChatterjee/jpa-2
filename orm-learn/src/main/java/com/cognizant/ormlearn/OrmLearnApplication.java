package com.cognizant.ormlearn;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.exception.CountryNotFoundException;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;

@SpringBootApplication(scanBasePackages = {"com.cognizant.ormlearn.service","com.cognizant.ormlearn.repository","com.cognizant.ormlearn.model"})
public class OrmLearnApplication {

	private static CountryService countryService;
	private static EmployeeService employeeService;
	private static DepartmentService departmentService;
	private static SkillService skillService;

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);
		countryService = context.getBean(CountryService.class);
		employeeService=context.getBean(EmployeeService.class);
		departmentService=context.getBean(DepartmentService.class);
		skillService=context.getBean(SkillService.class);
		testAddSkillToEmployee();
	}
	
	private static void testGetAllCountries() {
		LOGGER.info("Start");
		List<Country> countries = countryService.getAllCountries();
		LOGGER.debug("countries={}", countries);
		LOGGER.info("End");
	}
	private static void getAllCountryCodeTest() throws CountryNotFoundException {
		LOGGER.info("Start");
		Country country = countryService.findCountryByCode("AS");
		LOGGER.debug("Country={}", country);
		LOGGER.info("End");
	}
	private static void testAddCountry() {
		LOGGER.info("Start");
		Country country=new Country("XY","Xyzab");
		countryService.addCountry(country);
		try {
			LOGGER.debug("Country={}",countryService.findCountryByCode(country.getCountryCode()));
		} catch (CountryNotFoundException e) {
			e.printStackTrace();
		}
		LOGGER.info("End");
	}
	
	private static void testUpdateCountry() {
		LOGGER.info("Start");
		Country country=new Country("XY","Abcde");
		countryService.updateCountry(country);
		try {
			LOGGER.debug("Country={}",countryService.findCountryByCode(country.getCountryCode()));
		} catch (CountryNotFoundException e) {
			e.printStackTrace();
		}
		LOGGER.info("End");
	}
	
	private static void testDeleteCountry() {
		LOGGER.info("Start");
		countryService.deleteCountry("XY");
		LOGGER.info("End");
	}
	
	private static void testGetEmployee() {
		LOGGER.info("Start");
		Employee employee=employeeService.get(1);
		LOGGER.debug("Employee:{}",employee);
		LOGGER.debug("Department:{}",employee.getDepartment());
		LOGGER.debug("Skills:{}",employee.getSkills().toString());
		LOGGER.info("End");
	}
	private static void testGetDepartment() {
		LOGGER.info("Start");
		Department department=departmentService.get(3);
		LOGGER.debug("Department:{}",department);
		LOGGER.debug("Employees:{}",department.getEmployees().toString());
		LOGGER.info("End");
	}
	private static void testAddEmployee() {
		LOGGER.info("Start");
		Department department=departmentService.get(1);
		LOGGER.debug("Department:{}",department);
		
		Employee employee=new Employee();
		employee.setName("Saivi");
		employee.setSalary(10000.0);
		employee.setDateOfBirth(new Date());
		employee.setPermanent(true);
		employee.setDepartment(department);
		
		employeeService.save(employee);
		LOGGER.info("End");
	}
	private static void testUpdateEmployee() {
		LOGGER.info("Start");
		Employee employee=employeeService.get(5);
		LOGGER.debug("Employee:{}",employee);
		
		Department department=departmentService.get(3);
		LOGGER.debug("Department:{}",department);
		
		employee.setDepartment(department);
		
		employeeService.save(employee);
		LOGGER.info("End");
	}
	private static void testAddSkillToEmployee() {
		LOGGER.info("Start");
		Employee employee=employeeService.get(5);
		LOGGER.debug("Employee:{}",employee);
		
		Skill skill=skillService.get(3);
		LOGGER.debug("Skillt:{}",skill);
		
		employee.getSkills().add(skill);
		
		employeeService.save(employee);
		LOGGER.info("End");
	}
}
