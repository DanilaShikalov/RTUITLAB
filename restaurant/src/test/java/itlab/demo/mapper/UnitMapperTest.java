package itlab.demo.mapper;

import itlab.demo.model.dto.unit.*;
import itlab.demo.model.entity.UnitEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitMapperTest {
    private final UnitMapper unitMapper = Mappers.getMapper(UnitMapper.class);

    @Test
    void dtoToEntity() {
        PostUnitDTO postUnitDTO = new PostUnitDTO("Литр");
        UnitEntity unitEntity = unitMapper.dtoToEntity(postUnitDTO);
        assertEquals(postUnitDTO.getUnit(), unitEntity.getUnit());
    }

    @Test
    void entityToCreatedDTO() {
        UnitEntity unitEntity = new UnitEntity(null, "Литр");
        PostedUnitDTO postedUnitDTO = unitMapper.entityToCreatedDTO(unitEntity);
        assertEquals(unitEntity.getUnit(), postedUnitDTO.getUnit());
    }

    @Test
    void putDTOToEntity() {
        PutUnitDTO putUnitDTO = new PutUnitDTO("Литр");
        UnitEntity unitEntity = unitMapper.putDTOToEntity(putUnitDTO);
        assertEquals(putUnitDTO.getUnit(), unitEntity.getUnit());
    }

    @Test
    void entityToPutDTO() {
        UnitEntity unitEntity = new UnitEntity(null, "Литр");
        PuttedUnitDTO puttedUnitDTO = unitMapper.entityToPutDTO(unitEntity);
        assertEquals(unitEntity.getUnit(), puttedUnitDTO.getUnit());
    }

    @Test
    void entityToDTO() {
        UnitEntity unitEntity = new UnitEntity(null, "Литр");
        GetUnitDTO getUnitDTO = unitMapper.entityToDTO(unitEntity);
        assertEquals(unitEntity.getId(), getUnitDTO.getId());
    }

    @Test
    void listEntityToListDTO() {
        List<UnitEntity> list = List.of(new UnitEntity(1L, "Литр"));
        List<GetUnitDTO> list1 = unitMapper.listEntityToListDTO(list);
        assertEquals(list.get(0).getId(), list1.get(0).getId());
    }
}