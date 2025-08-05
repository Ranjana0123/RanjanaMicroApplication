package com.microservice.department;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.department.entity.DepartmentEntity;
import com.microservice.department.services.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class DepatmentControllerTests {
    @Autowired
    private MockMvc mockMvc;
    private DepartmentService departmentService;
    @Autowired
    private ObjectMapper objectMapper;
   /* @Test
    public void createDepartemnt() throws Exception{
        DepartmentEntity department =DepartmentEntity.builder()
                .departmentCode("56").departmentName("seeta")
                .departmentAddress("GH7").build();
        given(departmentService.saveDepartment(any(DepartmentEntity.class))).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        ResultActions response=mockMvc.perform(post("/api/departments").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(department)));
response.andDo(print()).andExpect(status().isCreated()).andExpect(
                (jsonPath("$.departmentCode",
                is(department.getDepartmentCode()))))
                .andExpect(jsonPath("$.departmentAddress",
                        is(department.getDepartmentAddress())))
                .andExpect(jsonPath("$.departmentName",
                        is(department.getDepartmentName())));
    }*/
    @Test
    public void getDepartment() throws Exception {
        DepartmentEntity department=new DepartmentEntity("2","ram","GH7");
        Mockito.when(departmentService.getDepartmentById(1L)).thenReturn(department);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().string("User-1"));
    }

}
