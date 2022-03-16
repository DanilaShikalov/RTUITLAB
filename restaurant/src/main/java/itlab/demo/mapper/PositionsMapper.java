package itlab.demo.mapper;

import itlab.demo.model.dto.positions.*;
import itlab.demo.model.entity.PositionsEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface PositionsMapper {
    PositionsEntity dtoToEntity(PostPositionsDTO postClientDTO);

    PostedPositionsDTO entityToCreatedDTO(PositionsEntity positionsEntity);

    PositionsEntity putDTOToEntity(PutPositionsDTO putPositionsDTO);

    PuttedPositionsDTO entityToPutDTO(PositionsEntity positionsEntity);

    GetPositionsDTO entityToDTO(PositionsEntity positionsEntity);

    List<GetPositionsDTO> listEntityToListDTO(List<PositionsEntity> listPositions);
}
