package itlab.demo.mapper;

import itlab.demo.model.dto.dish.*;
import itlab.demo.model.entity.DishEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DishMapperTest {
    private final DishMapper dishMapper = Mappers.getMapper(DishMapper.class);

    @Test
    void dtoToEntity() {
        PostDishDTO postDishDTO = new PostDishDTO(1L, 1L);
        DishEntity dishEntity = dishMapper.dtoToEntity(postDishDTO);
        assertEquals(postDishDTO.getId_menu(), dishEntity.getId_menu());
    }

    @Test
    void entityToCreatedDTO() {
        DishEntity dishEntity = new DishEntity(null, 1L, 1L, null, null);
        PostedDishDTO postedDishDTO = dishMapper.entityToCreatedDTO(dishEntity);
        assertEquals(dishEntity.getId_menu(), postedDishDTO.getId_menu());
        assertEquals(dishEntity.getId_orders(), postedDishDTO.getId_orders());
    }

    @Test
    void putDTOToEntity() {
        PutDishDTO putDishDTO = new PutDishDTO(1L, 1L);
        DishEntity dishEntity = dishMapper.putDTOToEntity(putDishDTO);
        assertEquals(putDishDTO.getId_menu(), dishEntity.getId_menu());
        assertEquals(putDishDTO.getId_orders(), dishEntity.getId_orders());
    }

    @Test
    void entityToPutDTO() {
        DishEntity dishEntity = new DishEntity(null, 1L, 1L, null, null);
        PuttedDishDTO puttedDishDTO = dishMapper.entityToPutDTO(dishEntity);
        assertEquals(dishEntity.getId_menu(), puttedDishDTO.getId_menu());
        assertEquals(dishEntity.getId_orders(), puttedDishDTO.getId_orders());
    }

    @Test
    void entityToDTO() {
        DishEntity dishEntity = new DishEntity(null, 1L, 1L, null, null);
        GetDishDTO getDishDTO = dishMapper.entityToDTO(dishEntity);
        assertEquals(dishEntity.getId(), getDishDTO.getId());
    }

    @Test
    void listEntityToListDTO() {
        List<DishEntity> list = List.of(new DishEntity(1L, 1L, 1L, null, null));
        List<GetDishDTO> list1 = dishMapper.listEntityToListDTO(list);
        assertEquals(list.get(0).getId(), list1.get(0).getId());
    }
}