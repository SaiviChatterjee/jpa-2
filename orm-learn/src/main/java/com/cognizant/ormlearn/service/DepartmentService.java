package com.cognizant.ormlearn.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.repository.DepartmentRepository;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Transactional
	public Department get(int id) {
		return departmentRepository.findById(id).get();
	}
	
	@Transactional
	public void save(Department department) {
		departmentRepository.save(department);
	}
}
