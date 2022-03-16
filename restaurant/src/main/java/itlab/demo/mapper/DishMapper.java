package itlab.demo.mapper;

import itlab.demo.model.dto.dish.*;
import itlab.demo.model.entity.DishEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface DishMapper {
    DishEntity dtoToEntity(PostDishDTO postClientDTO);

    PostedDishDTO entityToCreatedDTO(DishEntity dishEntity);

    DishEntity putDTOToEntity(PutDishDTO putDishDTO);

    PuttedDishDTO entityToPutDTO(DishEntity dishEntity);

    GetDishDTO entityToDTO(DishEntity dishEntity);

    List<GetDishDTO> listEntityToListDTO(List<DishEntity> listDish);
}
