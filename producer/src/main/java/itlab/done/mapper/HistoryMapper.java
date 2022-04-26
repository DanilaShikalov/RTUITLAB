package itlab.done.mapper;

import itlab.done.model.dto.history.*;
import itlab.done.model.entity.HistoryEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface HistoryMapper {
    HistoryEntity dtoToEntity(PostHistoryDTO postClientDTO);

    PostedHistoryDTO entityToCreatedDTO(HistoryEntity historyEntity);

    HistoryEntity putDTOToEntity(PutHistoryDTO putHistoryDTO);

    PuttedHistoryDTO entityToPutDTO(HistoryEntity historyEntity);

    GetHistoryDTO entityToDTO(HistoryEntity historyEntity);

    List<GetHistoryDTO> listEntityToListDTO(List<HistoryEntity> listHistory);
}
