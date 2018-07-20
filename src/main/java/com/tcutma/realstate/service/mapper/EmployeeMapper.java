package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.EmployeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Employee and its DTO EmployeeDTO.
 */
@Mapper(componentModel = "spring", uses = {ContactMapper.class, PhotoMapper.class, JobTitleMapper.class, DepartmentMapper.class})
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {

    @Mapping(source = "contact.id", target = "contactId")
    @Mapping(source = "photo.id", target = "photoId")
    @Mapping(source = "jobtitle.id", target = "jobtitleId")
    @Mapping(source = "jobtitle.titleName", target = "jobtitleTitleName")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.departmentName", target = "departmentDepartmentName")
    @Mapping(source = "manager.id", target = "managerId")
    @Mapping(source = "manager.employeeName", target = "managerEmployeeName")
    EmployeeDTO toDto(Employee employee);

    @Mapping(source = "contactId", target = "contact")
    @Mapping(source = "photoId", target = "photo")
    @Mapping(source = "jobtitleId", target = "jobtitle")
    @Mapping(source = "departmentId", target = "department")
    @Mapping(source = "managerId", target = "manager")
    Employee toEntity(EmployeeDTO employeeDTO);

    default Employee fromId(Long id) {
        if (id == null) {
            return null;
        }
        Employee employee = new Employee();
        employee.setId(id);
        return employee;
    }
}
