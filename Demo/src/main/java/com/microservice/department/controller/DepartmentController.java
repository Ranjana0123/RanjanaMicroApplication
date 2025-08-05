package com.microservice.department.controller;

import com.microservice.department.entity.DepartmentEntity;
import com.microservice.department.entity.InvalidDataExceptionImpl;
import com.microservice.department.entity.ResourceNotFoundException;
import com.microservice.department.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    ResponseEntity<DepartmentEntity> saveDepartment(@RequestBody DepartmentEntity department) {

        DepartmentEntity saveDepartment = departmentService.saveDepartment(department);
        return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    ResponseEntity<DepartmentEntity> getDepartmentById(@PathVariable("id") Long departmentId) {
        DepartmentEntity department = departmentService.getDepartmentById(departmentId);
/*        if("missing".equalsIgnoreCase(String.valueOf(departmentId))){
            throw  new ResourceNotFoundException("Resource '" + departmentId + "' not found");
        }
        if (departmentId < 3) {
            throw new InvalidDataExceptionImpl("Id must have at least 3 characters");
        }*/
        return ResponseEntity.ok(department);
    }

}
