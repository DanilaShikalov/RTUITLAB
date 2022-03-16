package itlab.demo.mapper;

import itlab.demo.model.dto.ingredient.*;
import itlab.demo.model.entity.IngredientEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface IngredientMapper {
    IngredientEntity dtoToEntity(PostIngredientDTO postClientDTO);

    PostedIngredientDTO entityToCreatedDTO(IngredientEntity ingredientEntity);

    IngredientEntity putDTOToEntity(PutIngredientDTO putIngredientDTO);

    PuttedIngredientDTO entityToPutDTO(IngredientEntity ingredientEntity);

    GetIngredientDTO entityToDTO(IngredientEntity ingredientEntity);

    List<GetIngredientDTO> listEntityToListDTO(List<IngredientEntity> listIngredient);
}
