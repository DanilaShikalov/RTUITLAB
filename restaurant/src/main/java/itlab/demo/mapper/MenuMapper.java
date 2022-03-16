package itlab.demo.mapper;

import itlab.demo.model.dto.menu.*;
import itlab.demo.model.dto.warehouse.GetWarehouseMenuDTO;
import itlab.demo.model.entity.MenuEntity;
import itlab.demo.model.entity.WarehouseEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface MenuMapper {
    MenuEntity dtoToEntity(PostMenuDTO postClientDTO);

    PostedMenuDTO entityToCreatedDTO(MenuEntity menuEntity);

    MenuEntity putDTOToEntity(PutMenuDTO putMenuDTO);

    PuttedMenuDTO entityToPutDTO(MenuEntity menuEntity);

    GetMenuDTO entityToDTO(MenuEntity menuEntity);

    GetMenuOrdersDTO entityToMenuOrdersDTO(MenuEntity menuEntity);

    List<GetMenuDTO> listEntityToListDTO(List<MenuEntity> listMenu);
}
