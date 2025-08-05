package com.microservice.department;

import com.microservice.department.repository.DepartmentRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentListener {

    @Autowired
    private DepartmentRepository repository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receive(Long deptId) {
        repository.findById(deptId).ifPresentOrElse(
                dept -> System.out.println("📦 Department found: " + dept.getDepartmentName()),
                () -> System.out.println("❌ Department not found for ID: " + deptId)
        );
    }
}

