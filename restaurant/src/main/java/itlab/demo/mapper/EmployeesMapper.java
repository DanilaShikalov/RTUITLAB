package itlab.demo.mapper;

import itlab.demo.model.dto.employees.*;
import itlab.demo.model.entity.EmployeesEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface EmployeesMapper {
    EmployeesEntity dtoToEntity(PostEmployeesDTO postClientDTO);

    PostedEmployeesDTO entityToCreatedDTO(EmployeesEntity employeesEntity);

    EmployeesEntity putDTOToEntity(PutEmployeesDTO putEmployeesDTO);

    PuttedEmployeesDTO entityToPutDTO(EmployeesEntity employeesEntity);

    GetEmployeesDTO entityToDTO(EmployeesEntity employeesEntity);

    List<GetEmployeesDTO> listEntityToListDTO(List<EmployeesEntity> listEmployees);
}
