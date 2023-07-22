package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.error.DepartmentNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository repository;

    @Override
    public Department saveDepartment(Department department) {
        return repository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return repository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
        Optional<Department> department=
                repository.findById(departmentId);
        if(department.isEmpty()){
            throw new DepartmentNotFoundException("Department Not Available");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        repository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department department) {
        Department departmentFromDB=repository.findById(departmentId).get();
        if(department.getDepartmentName()!=null){
            departmentFromDB.setDepartmentName(department.getDepartmentName());
        }
        if(department.getDepartmentCode()!=null){
            departmentFromDB.setDepartmentCode(department.getDepartmentCode());
        }
        if(department.getDepartmentAddress()!=null){
            departmentFromDB.setDepartmentAddress(department.getDepartmentAddress());
        }
        return repository.save(departmentFromDB);
    }

    @Override
    public Department fetchDepartmentByName(String department) {
        return repository.findByDepartmentNameIgnoreCase(department);
    }
}
