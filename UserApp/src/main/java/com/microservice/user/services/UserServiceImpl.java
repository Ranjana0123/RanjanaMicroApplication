package com.microservice.user.services;

import com.microservice.user.entity.DepartmentDto;
import com.microservice.user.entity.ResponseDto;
import com.microservice.user.entity.User;
import com.microservice.user.entity.UserDto;
import com.microservice.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final WebClient.Builder webClientBuilder;
    private final String departmentServiceUrl;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           WebClient.Builder webClientBuilder,
                           @Value("${department.service.url}") String departmentServiceUrl) {
        this.userRepository = userRepository;
        this.webClientBuilder = webClientBuilder;
        this.departmentServiceUrl = departmentServiceUrl;
    }

    public User saveUser(User department) {
        return userRepository.save(department);
    }

    @Override
    public ResponseDto getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        UserDto userDto = mapToUser(user);

        DepartmentDto dept = webClientBuilder.build()
                .get()
                .uri(departmentServiceUrl + "/" + user.getDepartmentId())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

        ResponseDto responseDto = new ResponseDto();
        responseDto.setUser(userDto);
        responseDto.setDepartment(dept);
        return responseDto;
    }

    private UserDto mapToUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserAddress(user.getFirstName());
        userDto.setUserName(user.getLastName());
        userDto.setUseCode(user.getEmail());
        return userDto;
    }
}