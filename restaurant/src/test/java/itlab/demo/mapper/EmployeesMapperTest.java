package itlab.demo.mapper;

import itlab.demo.model.dto.employees.*;
import itlab.demo.model.entity.EmployeesEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeesMapperTest {
    private final EmployeesMapper employeesMapper = Mappers.getMapper(EmployeesMapper.class);

    @Test
    void dtoToEntity() {
        PostEmployeesDTO postEmployeesDTO = new PostEmployeesDTO(1L, "Danila", "Shikalov", "Denisovich", "88005553535");
        EmployeesEntity employeesEntity = employeesMapper.dtoToEntity(postEmployeesDTO);
        assertEquals(postEmployeesDTO.getName(), employeesEntity.getName());
        assertEquals(postEmployeesDTO.getSecond_name(), employeesEntity.getSecond_name());
        assertEquals(postEmployeesDTO.getSurname(), employeesEntity.getSurname());
        assertEquals(postEmployeesDTO.getNumber(), employeesEntity.getNumber());
    }

    @Test
    void entityToCreatedDTO() {
        EmployeesEntity employeesEntity = new EmployeesEntity(null, 1L, "Danila", "Shikalov", "Denisovich", "88005553535", null, null);
        PostedEmployeesDTO postedEmployeesDTO = employeesMapper.entityToCreatedDTO(employeesEntity);
        assertEquals(postedEmployeesDTO.getName(), employeesEntity.getName());
        assertEquals(postedEmployeesDTO.getSecond_name(), employeesEntity.getSecond_name());
        assertEquals(postedEmployeesDTO.getSurname(), employeesEntity.getSurname());
        assertEquals(postedEmployeesDTO.getNumber(), employeesEntity.getNumber());
    }

    @Test
    void putDTOToEntity() {
        PutEmployeesDTO putEmployeesDTO = new PutEmployeesDTO(1L, "Danila", "Shikalov", "Denisovich", "88005553535");
        EmployeesEntity employeesEntity = employeesMapper.putDTOToEntity(putEmployeesDTO);
        assertEquals(putEmployeesDTO.getName(), employeesEntity.getName());
        assertEquals(putEmployeesDTO.getSecond_name(), employeesEntity.getSecond_name());
        assertEquals(putEmployeesDTO.getSurname(), employeesEntity.getSurname());
        assertEquals(putEmployeesDTO.getNumber(), employeesEntity.getNumber());
    }

    @Test
    void entityToPutDTO() {
        EmployeesEntity employeesEntity = new EmployeesEntity(null, 1L, "Danila", "Shikalov", "Denisovich", "88005553535", null, null);
        PuttedEmployeesDTO puttedEmployeesDTO = employeesMapper.entityToPutDTO(employeesEntity);
        assertEquals(puttedEmployeesDTO.getName(), employeesEntity.getName());
        assertEquals(puttedEmployeesDTO.getSecond_name(), employeesEntity.getSecond_name());
        assertEquals(puttedEmployeesDTO.getSurname(), employeesEntity.getSurname());
        assertEquals(puttedEmployeesDTO.getNumber(), employeesEntity.getNumber());
    }

    @Test
    void entityToDTO() {
        EmployeesEntity employeesEntity = new EmployeesEntity(null, 1L, "Danila", "Shikalov", "Denisovich", "88005553535", null, null);
        GetEmployeesDTO getEmployeesDTO = employeesMapper.entityToDTO(employeesEntity);
        assertEquals(getEmployeesDTO.getName(), employeesEntity.getName());
        assertEquals(getEmployeesDTO.getSecond_name(), employeesEntity.getSecond_name());
        assertEquals(getEmployeesDTO.getSurname(), employeesEntity.getSurname());
        assertEquals(getEmployeesDTO.getNumber(), employeesEntity.getNumber());
    }

    @Test
    void listEntityToListDTO() {
        List<EmployeesEntity> list = List.of(new EmployeesEntity(1L, 1L, "Danila", "Shikalov", "Denisovich", "88005553535", null, null));
        List<GetEmployeesDTO> list1 = employeesMapper.listEntityToListDTO(list);
        assertEquals(list1.get(0).getName(), list.get(0).getName());
        assertEquals(list1.get(0).getSecond_name(), list.get(0).getSecond_name());
        assertEquals(list1.get(0).getSurname(), list.get(0).getSurname());
        assertEquals(list1.get(0).getNumber(), list.get(0).getNumber());
    }
}