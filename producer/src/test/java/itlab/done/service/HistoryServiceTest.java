package itlab.done.service;

import itlab.done.dao.HistoryRepository;
import itlab.done.dao.ProductRepository;
import itlab.done.mapper.HistoryMapper;
import itlab.done.model.dto.history.PostHistoryDTO;
import itlab.done.model.entity.HistoryEntity;
import itlab.done.model.entity.ProductEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HistoryServiceTest {
    private AutoCloseable autoCloseable;
    private HistoryService underTest;
    @Mock
    private HistoryRepository historyRepository;
    @Mock
    private HistoryMapper historyMapper;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private HistoryEntity historyEntity;
    @Mock
    private ProductEntity productEntity;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new HistoryService(historyRepository, historyMapper, productRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void canCreateHistory() {
        // given
        PostHistoryDTO postHistoryDTO = new PostHistoryDTO(historyEntity.getId_product(), historyEntity.getAmount(),
                historyEntity.getDate_delivery());
        HistoryEntity history = historyMapper.dtoToEntity(postHistoryDTO);
        given(productRepository.findById(historyEntity.getId_product())).willReturn(Optional.of(productEntity));
        //when
        underTest.createHistory(postHistoryDTO);

        //then
        ArgumentCaptor<HistoryEntity> historyEntityArgumentCaptor =
                ArgumentCaptor.forClass(HistoryEntity.class);
        verify(historyRepository).save(historyEntityArgumentCaptor.capture());

        HistoryEntity historyEntity = historyEntityArgumentCaptor.getValue();
        assertThat(historyEntity).isEqualTo(history);
    }

    @Test
    void getHistory() {
        // given
        Long id = historyEntity.getId();
        given(historyRepository.findById(id)).willReturn(Optional.of(historyEntity));
        //when
        underTest.getHistory(id);

        //then
        ArgumentCaptor<Long> longArgumentCaptor =
                ArgumentCaptor.forClass(Long.class);
        verify(historyRepository).findById(longArgumentCaptor.capture());
        Long capturedId = longArgumentCaptor.getValue();
        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void canGetAllHistory() {
        underTest.getAllHistory();

        verify(historyRepository).findAll();
    }

}