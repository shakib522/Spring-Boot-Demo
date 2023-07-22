package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department department=
                Department.builder()
                        .departmentName("ICE")
                        .departmentAddress("Rajshahi")
                        .departmentCode("ICE-RU")
                        .departmentId(1L)
                        .build();

        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("ICE"))
                .thenReturn(department);
    }

    @Test
    @DisplayName("Get Data based on valid department Name")
    public void whenValidDepartmentName_thenDepartmentShouldFound(){
        String departmentName="ICE";
        Department found=
                departmentService.fetchDepartmentByName(departmentName);
         assertEquals(departmentName,found.getDepartmentName());
    }

}