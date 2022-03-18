package itlab.demo.mapper;

import itlab.demo.model.dto.menu.*;
import itlab.demo.model.entity.MenuEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MenuMapperTest {
    private final MenuMapper menuMapper = Mappers.getMapper(MenuMapper.class);

    @Test
    void dtoToEntity() {
        PostMenuDTO postMenuDTO = new PostMenuDTO(1L, "Pizza", 500, 3);
        MenuEntity menuEntity = menuMapper.dtoToEntity(postMenuDTO);
        assertEquals(postMenuDTO.getTitle(), menuEntity.getTitle());
        assertEquals(postMenuDTO.getPrice(), menuEntity.getPrice());
        assertEquals(postMenuDTO.getWeight(), menuEntity.getWeight());
    }

    @Test
    void entityToCreatedDTO() {
        MenuEntity menuEntity = new MenuEntity(null, 1L, "Pizza", 500, 3, null, null, null);
        PostedMenuDTO postedMenuDTO = menuMapper.entityToCreatedDTO(menuEntity);
        assertEquals(postedMenuDTO.getTitle(), menuEntity.getTitle());
        assertEquals(postedMenuDTO.getPrice(), menuEntity.getPrice());
        assertEquals(postedMenuDTO.getWeight(), menuEntity.getWeight());
    }

    @Test
    void putDTOToEntity() {
        PutMenuDTO putMenuDTO = new PutMenuDTO(1L, "Pizza", 500, 3);
        MenuEntity menuEntity = menuMapper.putDTOToEntity(putMenuDTO);
        assertEquals(putMenuDTO.getTitle(), menuEntity.getTitle());
        assertEquals(putMenuDTO.getPrice(), menuEntity.getPrice());
        assertEquals(putMenuDTO.getWeight(), menuEntity.getWeight());
    }

    @Test
    void entityToPutDTO() {
        MenuEntity menuEntity = new MenuEntity(null, 1L, "Pizza", 500, 3, null, null, null);
        PuttedMenuDTO puttedMenuDTO = menuMapper.entityToPutDTO(menuEntity);
        assertEquals(puttedMenuDTO.getTitle(), menuEntity.getTitle());
        assertEquals(puttedMenuDTO.getPrice(), menuEntity.getPrice());
        assertEquals(puttedMenuDTO.getWeight(), menuEntity.getWeight());
    }

    @Test
    void entityToDTO() {
        MenuEntity menuEntity = new MenuEntity(null, 1L, "Pizza", 500, 3, null, null, null);
        GetMenuDTO getMenuDTO = menuMapper.entityToDTO(menuEntity);
        assertEquals(menuEntity.getId(), getMenuDTO.getId());
    }

    @Test
    void listEntityToListDTO() {
        List<MenuEntity> list = List.of(new MenuEntity(null, 1L, "Pizza", 500, 3, null, null, null));
        List<GetMenuDTO> list1 = menuMapper.listEntityToListDTO(list);
        assertEquals(list.get(0).getId(), list1.get(0).getId());
    }

    @Test
    void entityToMenuOrdersDTO()
    {
        MenuEntity menuEntity = new MenuEntity(null, 1L, "Pizza", 500, 3, null, null, null);
        GetMenuOrdersDTO getMenuOrdersDTO = menuMapper.entityToMenuOrdersDTO(menuEntity);
        assertEquals(getMenuOrdersDTO.getTitle(), menuEntity.getTitle());
        assertEquals(getMenuOrdersDTO.getPrice(), menuEntity.getPrice());
        assertEquals(getMenuOrdersDTO.getWeight(), menuEntity.getWeight());
    }
}