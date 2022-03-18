package itlab.demo.mapper;

import itlab.demo.model.dto.positions.*;
import itlab.demo.model.entity.PositionsEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionsMapperTest {
    private final PositionsMapper positionsMapper = Mappers.getMapper(PositionsMapper.class);

    @Test
    void dtoToEntity() {
        PostPositionsDTO postPositionsDTO = new PostPositionsDTO("Официант", 25000);
        PositionsEntity positionsEntity = positionsMapper.dtoToEntity(postPositionsDTO);
        assertEquals(postPositionsDTO.getPosition(), positionsEntity.getPosition());
        assertEquals(postPositionsDTO.getSalary(), positionsEntity.getSalary());
    }

    @Test
    void entityToCreatedDTO() {
        PositionsEntity positionsEntity = new PositionsEntity(null, "Официант", 25000, null);
        PostedPositionsDTO postedPositionsDTO = positionsMapper.entityToCreatedDTO(positionsEntity);
        assertEquals(postedPositionsDTO.getPosition(), positionsEntity.getPosition());
        assertEquals(postedPositionsDTO.getSalary(), positionsEntity.getSalary());
    }

    @Test
    void putDTOToEntity() {
        PutPositionsDTO putPositionsDTO = new PutPositionsDTO("Официант", 25000);
        PositionsEntity positionsEntity = positionsMapper.putDTOToEntity(putPositionsDTO);
        assertEquals(putPositionsDTO.getPosition(), positionsEntity.getPosition());
        assertEquals(putPositionsDTO.getSalary(), positionsEntity.getSalary());
    }

    @Test
    void entityToPutDTO() {
        PositionsEntity positionsEntity = new PositionsEntity(null, "Официант", 25000, null);
        PuttedPositionsDTO puttedPositionsDTO = positionsMapper.entityToPutDTO(positionsEntity);
        assertEquals(puttedPositionsDTO.getPosition(), positionsEntity.getPosition());
        assertEquals(puttedPositionsDTO.getSalary(), positionsEntity.getSalary());
    }

    @Test
    void entityToDTO() {
        PositionsEntity positionsEntity = new PositionsEntity(null, "Официант", 25000, null);
        GetPositionsDTO getPositionsDTO = positionsMapper.entityToDTO(positionsEntity);
        assertEquals(positionsEntity.getId(), getPositionsDTO.getId());
    }

    @Test
    void listEntityToListDTO() {
        List<PositionsEntity> list = List.of(new PositionsEntity(1L, "Официант", 25000, null));
        List<GetPositionsDTO> list1 = positionsMapper.listEntityToListDTO(list);
        assertEquals(list.get(0).getId(), list1.get(0).getId());
    }
}