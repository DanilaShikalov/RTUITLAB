package itlab.demo.service;

import itlab.demo.dao.EmployeesRepository;
import itlab.demo.dao.PositionsRepository;
import itlab.demo.exception.EmployeesNotFoundException;
import itlab.demo.exception.PositionsNotFoundException;
import itlab.demo.mapper.EmployeesMapper;
import itlab.demo.model.dto.employees.*;
import itlab.demo.model.entity.EmployeesEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeesService {
    private final EmployeesRepository employeesRepository;
    private final EmployeesMapper employeesMapper;
    private final PositionsRepository positionsRepository;

    public PostedEmployeesDTO createEmployees(PostEmployeesDTO postEmployeesDTO) {
        positionsRepository.findById(postEmployeesDTO.getId_position()).orElseThrow(() -> new PositionsNotFoundException(postEmployeesDTO.getId_position()));
        EmployeesEntity entity = employeesMapper.dtoToEntity(postEmployeesDTO);
        EmployeesEntity save = employeesRepository.save(entity);
        return employeesMapper.entityToCreatedDTO(save);
    }

    public GetEmployeesDTO getEmployees(Long id) {
        EmployeesEntity employeesEntity = employeesRepository.findById(id).orElseThrow(() -> new EmployeesNotFoundException(id));
        return employeesMapper.entityToDTO(employeesEntity);
    }

    public List<GetEmployeesDTO> getAllEmployees()
    {
        List<EmployeesEntity> list = employeesRepository.findAll();
        return employeesMapper.listEntityToListDTO(list);
    }

    public void deleteEmployees(Long id)
    {
        employeesRepository.findById(id).orElseThrow(() -> new EmployeesNotFoundException(id));
        employeesRepository.deleteById(id);
    }

    public PuttedEmployeesDTO putEmployees(PutEmployeesDTO putEmployeesDTO, Long id)
    {
        positionsRepository.findById(putEmployeesDTO.getId_position()).orElseThrow(() -> new PositionsNotFoundException(putEmployeesDTO.getId_position()));
        employeesRepository.findById(id).orElseThrow(() -> new EmployeesNotFoundException(id));
        EmployeesEntity put = employeesMapper.putDTOToEntity(putEmployeesDTO);
        put.setId(id);
        employeesRepository.save(put);
        return employeesMapper.entityToPutDTO(put);
    }
}
