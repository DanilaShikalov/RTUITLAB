package itlab.demo.mapper;

import itlab.demo.model.dto.warehouse.*;
import itlab.demo.model.entity.WarehouseEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WarehouseMapperTest {
    private final WarehouseMapper warehouseMapper = Mappers.getMapper(WarehouseMapper.class);

    @Test
    void dtoToEntity() {
        PostWarehouseDTO postWarehouseDTO = new PostWarehouseDTO(1L, 1L, "Перец", 50, 7, 130);
        WarehouseEntity warehouseEntity = warehouseMapper.dtoToEntity(postWarehouseDTO);
        assertEquals(postWarehouseDTO.getTitle(), warehouseEntity.getTitle());
        assertEquals(postWarehouseDTO.getWeight(), warehouseEntity.getWeight());
        assertEquals(postWarehouseDTO.getAmount(), warehouseEntity.getAmount());
        assertEquals(postWarehouseDTO.getPrice(), warehouseEntity.getPrice());
    }

    @Test
    void entityToCreatedDTO() {
        WarehouseEntity warehouseEntity = new WarehouseEntity(null, 1L, 1L, "Перец", 50, 7, 130, null, null);
        PostedWarehouseDTO postedWarehouseDTO = warehouseMapper.entityToCreatedDTO(warehouseEntity);
        assertEquals(postedWarehouseDTO.getTitle(), warehouseEntity.getTitle());
        assertEquals(postedWarehouseDTO.getWeight(), warehouseEntity.getWeight());
        assertEquals(postedWarehouseDTO.getAmount(), warehouseEntity.getAmount());
        assertEquals(postedWarehouseDTO.getPrice(), warehouseEntity.getPrice());
    }

    @Test
    void putDTOToEntity() {
        PutWarehouseDTO putWarehouseDTO = new PutWarehouseDTO(1L, 1L, "Перец", 50, 7, 130);
        WarehouseEntity warehouseEntity = warehouseMapper.putDTOToEntity(putWarehouseDTO);
        assertEquals(putWarehouseDTO.getTitle(), warehouseEntity.getTitle());
        assertEquals(putWarehouseDTO.getWeight(), warehouseEntity.getWeight());
        assertEquals(putWarehouseDTO.getAmount(), warehouseEntity.getAmount());
        assertEquals(putWarehouseDTO.getPrice(), warehouseEntity.getPrice());
    }

    @Test
    void entityToPutDTO() {
        WarehouseEntity warehouseEntity = new WarehouseEntity(null, 1L, 1L, "Перец", 50, 7, 130, null, null);
        PuttedWarehouseDTO puttedWarehouseDTO = warehouseMapper.entityToPutDTO(warehouseEntity);
        assertEquals(puttedWarehouseDTO.getTitle(), warehouseEntity.getTitle());
        assertEquals(puttedWarehouseDTO.getWeight(), warehouseEntity.getWeight());
        assertEquals(puttedWarehouseDTO.getAmount(), warehouseEntity.getAmount());
        assertEquals(puttedWarehouseDTO.getPrice(), warehouseEntity.getPrice());
    }

    @Test
    void entityToDTO() {
        WarehouseEntity warehouseEntity = new WarehouseEntity(null, 1L, 1L, "Перец", 50, 7, 130, null, null);
        GetWarehouseDTO getWarehouseDTO = warehouseMapper.entityToDTO(warehouseEntity);
        assertEquals(warehouseEntity.getId(), getWarehouseDTO.getId());
    }

    @Test
    void listEntityToListDTO() {
        List<WarehouseEntity> list = List.of(new WarehouseEntity(1L, 1L, 1L, "Перец", 50, 7, 130, null, null));
        List<GetWarehouseDTO> list1 = warehouseMapper.listEntityToListDTO(list);
        assertEquals(list.get(0).getId(), list1.get(0).getId());
    }
}