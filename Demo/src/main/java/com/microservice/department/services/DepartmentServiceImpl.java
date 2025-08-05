package com.microservice.department.services;

import com.microservice.department.entity.DepartmentEntity;
import com.microservice.department.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    DepartmentRepository departmentRepository;
    public DepartmentEntity saveDepartment(DepartmentEntity department) {
        return departmentRepository.save(department);
    }
    @Override
    public DepartmentEntity getDepartmentById(Long id) {

        return departmentRepository.findById(id).get();
    }
}
