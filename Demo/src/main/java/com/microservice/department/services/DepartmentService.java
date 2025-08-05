package com.microservice.department.services;

import com.microservice.department.entity.DepartmentEntity;

public interface DepartmentService {
    DepartmentEntity saveDepartment(DepartmentEntity department);
    DepartmentEntity getDepartmentById(Long departmentId);
}
