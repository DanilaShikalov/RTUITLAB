package itlab.demo.mapper;

import itlab.demo.model.dto.dish.GetDishDTO;
import itlab.demo.model.dto.unit.*;
import itlab.demo.model.entity.DishEntity;
import itlab.demo.model.entity.UnitEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface UnitMapper {
    UnitEntity dtoToEntity(PostUnitDTO postClientDTO);

    PostedUnitDTO entityToCreatedDTO(UnitEntity unitEntity);

    UnitEntity putDTOToEntity(PutUnitDTO putUnitDTO);

    PuttedUnitDTO entityToPutDTO(UnitEntity unitEntity);

    GetUnitDTO entityToDTO(UnitEntity unitEntity);

    List<GetUnitDTO> listEntityToListDTO(List<UnitEntity> listUnit);
}
