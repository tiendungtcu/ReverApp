package com.tcutma.realstate.service.mapper;

import com.tcutma.realstate.domain.*;
import com.tcutma.realstate.service.dto.EmployeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Employee and its DTO EmployeeDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, DepartmentMapper.class, JobTitleMapper.class})
public interface EmployeeMapper extends EntityMapper<EmployeeDTO, Employee> {

    @Mapping(source = "account.id", target = "accountId")
    @Mapping(source = "account.login", target = "accountLogin")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.departmentName", target = "departmentDepartmentName")
    @Mapping(source = "jobtitle.id", target = "jobtitleId")
    @Mapping(source = "jobtitle.titleName", target = "jobtitleTitleName")
    EmployeeDTO toDto(Employee employee);

    @Mapping(source = "accountId", target = "account")
    @Mapping(source = "departmentId", target = "department")
    @Mapping(source = "jobtitleId", target = "jobtitle")
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
