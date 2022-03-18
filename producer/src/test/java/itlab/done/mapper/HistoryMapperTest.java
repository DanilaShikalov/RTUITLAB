package itlab.done.mapper;

import itlab.done.model.dto.history.*;
import itlab.done.model.entity.HistoryEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HistoryMapperTest {
    private final HistoryMapper historyMapper = Mappers.getMapper(HistoryMapper.class);

    @Test
    void dtoToEntity() {
        PostHistoryDTO postHistoryDTO = new PostHistoryDTO(1L, 23, null);
        HistoryEntity historyEntity = historyMapper.dtoToEntity(postHistoryDTO);
        assertEquals(postHistoryDTO.getAmount(), historyEntity.getAmount());
    }

    @Test
    void entityToCreatedDTO() {
        HistoryEntity historyEntity = new HistoryEntity(1L, 5L, 23, null);
        PostedHistoryDTO postedHistoryDTO = historyMapper.entityToCreatedDTO(historyEntity);
        assertEquals(historyEntity.getAmount(), postedHistoryDTO.getAmount());
        assertEquals(historyEntity.getId_product(), postedHistoryDTO.getId_product());
    }

    @Test
    void putDTOToEntity() {
        PutHistoryDTO putHistoryDTO = new PutHistoryDTO(1L, 23, null);
        HistoryEntity historyEntity = historyMapper.putDTOToEntity(putHistoryDTO);
        assertEquals(putHistoryDTO.getAmount(), historyEntity.getAmount());
        assertEquals(putHistoryDTO.getId_product(), historyEntity.getId_product());
    }

    @Test
    void entityToPutDTO() {
        HistoryEntity historyEntity = new HistoryEntity(1L, 5L, 23, null);
        PuttedHistoryDTO puttedHistoryDTO = historyMapper.entityToPutDTO(historyEntity);
        assertEquals(historyEntity.getAmount(), puttedHistoryDTO.getAmount());
        assertEquals(historyEntity.getId_product(), puttedHistoryDTO.getId_product());
    }

    @Test
    void entityToDTO() {
        HistoryEntity historyEntity = new HistoryEntity(1L, 5L, 23, null);
        GetHistoryDTO getHistoryDTO = historyMapper.entityToDTO(historyEntity);
        assertEquals(historyEntity.getAmount(), getHistoryDTO.getAmount());
    }

    @Test
    void listEntityToListDTO() {
        List<HistoryEntity> list = List.of(new HistoryEntity(1L, 5L, 23, null));
        List<GetHistoryDTO> list1 = historyMapper.listEntityToListDTO(list);
        assertEquals(list.get(0).getAmount(), list1.get(0).getAmount());
    }
}