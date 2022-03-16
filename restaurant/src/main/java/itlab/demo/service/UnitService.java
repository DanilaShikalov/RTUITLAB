package itlab.demo.service;

import itlab.demo.dao.UnitRepository;
import itlab.demo.exception.UnitNotFoundException;
import itlab.demo.exception.UnitNotRemoveException;
import itlab.demo.mapper.UnitMapper;
import itlab.demo.model.dto.dish.GetDishDTO;
import itlab.demo.model.dto.unit.*;
import itlab.demo.model.entity.DishEntity;
import itlab.demo.model.entity.UnitEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UnitService {
    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;

    public PostedUnitDTO createUnit(PostUnitDTO postUnitDTO) {
        UnitEntity entity = unitMapper.dtoToEntity(postUnitDTO);
        UnitEntity save = unitRepository.save(entity);
        return unitMapper.entityToCreatedDTO(save);
    }

    public GetUnitDTO getUnit(Long id) {
        UnitEntity unitEntity = unitRepository.findById(id).orElseThrow(() -> new UnitNotFoundException(id));
        return unitMapper.entityToDTO(unitEntity);
    }

    public List<GetUnitDTO> getAllUnit()
    {
        List<UnitEntity> list = unitRepository.findAll();
        return unitMapper.listEntityToListDTO(list);
    }

    public void deleteUnit(Long id)
    {
        unitRepository.findById(id).orElseThrow(() -> new UnitNotFoundException(id));
        try {
            unitRepository.deleteById(id);
        } catch (Exception e) {
            throw new UnitNotRemoveException();
        }
    }

    public PuttedUnitDTO putUnit(PutUnitDTO putUnitDTO, Long id)
    {
        unitRepository.findById(id).orElseThrow(() -> new UnitNotFoundException(id));
        UnitEntity put = unitMapper.putDTOToEntity(putUnitDTO);
        put.setId(id);
        unitRepository.save(put);
        return unitMapper.entityToPutDTO(put);
    }
}
