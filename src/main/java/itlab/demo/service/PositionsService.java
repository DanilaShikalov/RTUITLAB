package itlab.demo.service;

import itlab.demo.dao.PositionsRepository;
import itlab.demo.exception.PositionsNotFoundException;
import itlab.demo.mapper.PositionsMapper;
import itlab.demo.model.dto.positions.*;
import itlab.demo.model.entity.PositionsEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PositionsService {
    private final PositionsRepository positionsRepository;
    private final PositionsMapper positionsMapper;

    public PostedPositionsDTO createPositions(PostPositionsDTO postPositionsDTO) {
        PositionsEntity entity = positionsMapper.dtoToEntity(postPositionsDTO);
        PositionsEntity save = positionsRepository.save(entity);
        return positionsMapper.entityToCreatedDTO(save);
    }

    public GetPositionsDTO getPositions(Long id) {
        PositionsEntity positionsEntity = positionsRepository.findById(id).orElseThrow(() -> new PositionsNotFoundException(id));
        return positionsMapper.entityToDTO(positionsEntity);
    }

    public List<GetPositionsDTO> getAllPositions()
    {
        List<PositionsEntity> list = positionsRepository.findAll();
        return positionsMapper.listEntityToListDTO(list);
    }

    public void deletePositions(Long id)
    {
        positionsRepository.findById(id).orElseThrow(() -> new PositionsNotFoundException(id));
        positionsRepository.deleteById(id);
    }

    public PuttedPositionsDTO putPositions(PutPositionsDTO putPositionsDTO, Long id)
    {
        positionsRepository.findById(id).orElseThrow(() -> new PositionsNotFoundException(id));
        PositionsEntity put = positionsMapper.putDTOToEntity(putPositionsDTO);
        put.setId(id);
        positionsRepository.save(put);
        return positionsMapper.entityToPutDTO(put);
    }
}
