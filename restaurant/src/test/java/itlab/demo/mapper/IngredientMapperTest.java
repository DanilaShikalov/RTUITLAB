package itlab.demo.mapper;

import itlab.demo.model.dto.ingredient.*;
import itlab.demo.model.entity.IngredientEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientMapperTest {
    private final IngredientMapper ingredientMapper = Mappers.getMapper(IngredientMapper.class);

    @Test
    void dtoToEntity() {
        PostIngredientDTO postIngredientDTO = new PostIngredientDTO(1L, 1L);
        IngredientEntity ingredientEntity = ingredientMapper.dtoToEntity(postIngredientDTO);
        assertEquals(postIngredientDTO.getId_menu(), ingredientEntity.getId_menu());
    }

    @Test
    void entityToCreatedDTO() {
        IngredientEntity ingredientEntity = new IngredientEntity(null, 1L, 1L, null, null);
        PostedIngredientDTO postedIngredientDTO = ingredientMapper.entityToCreatedDTO(ingredientEntity);
        assertEquals(ingredientEntity.getId_menu(), postedIngredientDTO.getId_menu());
        assertEquals(ingredientEntity.getId_warehouse(), postedIngredientDTO.getId_warehouse());
    }

    @Test
    void putDTOToEntity() {
        PutIngredientDTO putIngredientDTO = new PutIngredientDTO(1L, 1L);
        IngredientEntity ingredientEntity = ingredientMapper.putDTOToEntity(putIngredientDTO);
        assertEquals(putIngredientDTO.getId_menu(), ingredientEntity.getId_menu());
        assertEquals(putIngredientDTO.getId_warehouse(), ingredientEntity.getId_warehouse());
    }

    @Test
    void entityToPutDTO() {
        IngredientEntity ingredientEntity = new IngredientEntity(null, 1L, 1L, null, null);
        PuttedIngredientDTO puttedIngredientDTO = ingredientMapper.entityToPutDTO(ingredientEntity);
        assertEquals(ingredientEntity.getId_menu(), puttedIngredientDTO.getId_menu());
        assertEquals(ingredientEntity.getId_warehouse(), puttedIngredientDTO.getId_warehouse());
    }

    @Test
    void entityToDTO() {
        IngredientEntity ingredientEntity = new IngredientEntity(null, 1L, 1L, null, null);
        GetIngredientDTO getIngredientDTO = ingredientMapper.entityToDTO(ingredientEntity);
        assertEquals(ingredientEntity.getId(), getIngredientDTO.getId());
    }

    @Test
    void listEntityToListDTO() {
        List<IngredientEntity> list = List.of(new IngredientEntity(1L, 1L, 1L, null, null));
        List<GetIngredientDTO> list1 = ingredientMapper.listEntityToListDTO(list);
        assertEquals(list.get(0).getId(), list1.get(0).getId());
    }
}